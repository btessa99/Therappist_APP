%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 08. feb 2022 23:06
%%%-------------------------------------------------------------------
-module(server_therappist).

-behaviour(gen_server).

%% API
-export([start/0, call_server/1, init/1, handle_call/3]).

-define(SERVER, ?MODULE).


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
  io:format("server_therappist/init()~n"),
  {ok, {}}.

%% @private
%% @doc Saves the incoming message in mnesia and pushes it to the rabbitmq reciever's queue
handle_call({message, {Timestamp, Sender, Receiver, Text}}, _From, _) ->
  mnesiaHandler:add_message(Timestamp, Sender, Receiver, Text),
  {reply, ack, _ = '_'};

%% @private
%% @doc Starts the rabbitmq listener for the logged user and retrieves the chat messages for the opened chat from mnesia
handle_call({init, {Pid, Username, Chatter, ClientNodeName}}, _From, _) ->
  io:format("init from ~p~n",[Username]),
    {reply, mnesiaHandler:get_messages(Username, Chatter), _ = '_'}.

%%%===================================================================
%%% Internal functions: NONE
%%%===================================================================
