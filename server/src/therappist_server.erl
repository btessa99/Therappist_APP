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
-export([start_link/0]).

%% gen_server callbacks
-export([init/1, handle_call/3, handle_cast/2, handle_info/2, terminate/2,
  code_change/3]).

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
%% @doc Handling call messages

handle_call({message, {Timestamp, Sender, Receiver, Text}}, _From, _) ->
  mnesiaHandler:add_message(Timestamp, Sender, Receiver, Text),
  rabbitmq_client:push({Timestamp, Sender, Receiver, Text}),
  {reply, ack, _ = '_'}.

%% @private
%% @doc Handling cast messages
-spec(handle_cast(Request :: term(), State :: #terappist_server_state{}) ->
  {noreply, NewState :: #terappist_server_state{}} |
  {noreply, NewState :: #terappist_server_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), NewState :: #terappist_server_state{}}).
handle_cast(_Request, State = #terappist_server_state{}) ->
  {noreply, State}.

%% @private
%% @doc Handling all non call/cast messages
-spec(handle_info(Info :: timeout() | term(), State :: #terappist_server_state{}) ->
  {noreply, NewState :: #terappist_server_state{}} |
  {noreply, NewState :: #terappist_server_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), NewState :: #terappist_server_state{}}).
handle_info(_Info, State = #terappist_server_state{}) ->
  {noreply, State}.

%% @private
%% @doc This function is called by a gen_server when it is about to
%% terminate. It should be the opposite of Module:init/1 and do any
%% necessary cleaning up. When it returns, the gen_server terminates
%% with Reason. The return value is ignored.
-spec(terminate(Reason :: (normal | shutdown | {shutdown, term()} | term()),
    State :: #terappist_server_state{}) -> term()).
terminate(_Reason, _State = #terappist_server_state{}) ->
  ok.

%% @private
%% @doc Convert process state when code is changed
-spec(code_change(OldVsn :: term() | {down, term()}, State :: #terappist_server_state{},
    Extra :: term()) ->
  {ok, NewState :: #terappist_server_state{}} | {error, Reason :: term()}).
code_change(_OldVsn, State = #terappist_server_state{}, _Extra) ->
  {ok, State}.

%%%===================================================================
%%% Internal functions
%%%===================================================================
