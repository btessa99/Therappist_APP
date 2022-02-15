%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 08. feb 2022 23:06
%%%-------------------------------------------------------------------
-module(therappist_server).

-behaviour(gen_server).

%% API
-export([start/0, call_server/1]).

-define(SERVER, ?MODULE).

-record(terappist_server_state, {}).

%%%===================================================================
%%% API
%%%===================================================================

%% @doc Spawns the server and registers the local name (unique)
start() ->
  gen_server:start({local, therappist_server}, ?MODULE, [], []).

call_server(Content) ->
  gen_server:call(therappist_server, Content).

%%%===================================================================
%%% gen_server callbacks
%%%===================================================================

%% @private
%% @doc Initializes the server

init(_) ->
  rabbitmq_client:start(),
  {ok, {}}.

%% @private
%% @doc Saves the incoming message in mnesia and pushes it to the rabbitmq reciever's queue
handle_call({message, {Timestamp, Sender, Receiver, Text}}, _From, _) ->
  mnesiaHandler:add_message(Timestamp, Sender, Receiver, Text),
  rabbitmq_client:push({Timestamp, Sender, Receiver, Text}),
  {reply, ack, _ = '_'};

%% @private
%% @doc Starts the rabbitmq listener for the logged user and retrieves the chat messages for the opened chat from mnesia
handle_call({log, {Pid, Username, Chatter, ClientNodeName}}, _From, _) ->
  case rabbitmq_client:request_consuming(Username, Pid) of
    consumer_created -> {reply, mnesiaHandler:get_messages(Username, Chatter), _ = '_'};
    _ -> {reply, [], _ = '_'}
  end;

%% @doc handles logout: terminates rabbitMQ consuming session
handle_call({logout, Username}, _From, _) ->
  {reply, rabbitmq_client:terminate_consuming_session(Username), _='_'}.

%% @private
%% @doc Handling cast messages
-spec(handle_cast(Request :: term(), State :: #terappist_server_state{}) ->
  {noreply, NewState :: #terappist_server_state{}} |
  {noreply, NewState :: #terappist_server_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), NewState :: #terappist_server_state{}}).
handle_cast(_Request, State = #terappist_server_state{}) ->
  {noreply, _ = '_'}.

%% @private
%% @doc This function is called by a gen_server when it is about to
%% terminate. It should be the opposite of Module:init/1 and do any
%% necessary cleaning up. When it returns, the gen_server terminates
%% with Reason. The return value is ignored.
terminate(_Reason, _State = #terappist_server_state{}) ->
  ok.

%%%===================================================================
%%% Internal functions: NONE
%%%===================================================================
