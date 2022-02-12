%%%-------------------------------------------------------------------
%%% @author pietro
%%% @copyright (C) 2022, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 12. feb 2022 17:32
%%%-------------------------------------------------------------------
-module(rabbitmq_client).
-author("pietro").

-behaviour(gen_server).

%% API
-export([start_link/0]).

%% gen_server callbacks
-export([init/1, handle_call/3, handle_cast/2, handle_info/2, terminate/2,
  code_change/3]).

-define(SERVER, ?MODULE).

-record(rabbitmq_erlang_state, {}).

%%%===================================================================
%%% API
%%%===================================================================

%% @doc Spawns the server and registers the local name (unique)
start_link() ->
  gen_server:start({local, rabbit_server}, ?MODULE, [], []).

%%%===================================================================
%%% gen_server callbacks
%%%===================================================================

%% @private
%% @doc Initializes the server

init(_) ->

  {ok, {}}.

%% @private
%% @doc Handling call messages
-spec(handle_call(Request :: term(), From :: {pid(), Tag :: term()},
    State :: #rabbitmq_erlang_state{}) ->
  {reply, Reply :: term(), NewState :: #rabbitmq_erlang_state{}} |
  {reply, Reply :: term(), NewState :: #rabbitmq_erlang_state{}, timeout() | hibernate} |
  {noreply, NewState :: #rabbitmq_erlang_state{}} |
  {noreply, NewState :: #rabbitmq_erlang_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), Reply :: term(), NewState :: #rabbitmq_erlang_state{}} |
  {stop, Reason :: term(), NewState :: #rabbitmq_erlang_state{}}).
handle_call(_Request, _From, State = #rabbitmq_erlang_state{}) ->
  {reply, ok, State}.

%% @private
%% @doc Handling cast messages
-spec(handle_cast(Request :: term(), State :: #rabbitmq_erlang_state{}) ->
  {noreply, NewState :: #rabbitmq_erlang_state{}} |
  {noreply, NewState :: #rabbitmq_erlang_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), NewState :: #rabbitmq_erlang_state{}}).
handle_cast(_Request, State = #rabbitmq_erlang_state{}) ->
  {noreply, State}.

%% @private
%% @doc Handling all non call/cast messages
-spec(handle_info(Info :: timeout() | term(), State :: #rabbitmq_erlang_state{}) ->
  {noreply, NewState :: #rabbitmq_erlang_state{}} |
  {noreply, NewState :: #rabbitmq_erlang_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), NewState :: #rabbitmq_erlang_state{}}).
handle_info(_Info, State = #rabbitmq_erlang_state{}) ->
  {noreply, State}.

%% @private
%% @doc This function is called by a gen_server when it is about to
%% terminate. It should be the opposite of Module:init/1 and do any
%% necessary cleaning up. When it returns, the gen_server terminates
%% with Reason. The return value is ignored.
-spec(terminate(Reason :: (normal | shutdown | {shutdown, term()} | term()),
    State :: #rabbitmq_erlang_state{}) -> term()).
terminate(_Reason, _State = #rabbitmq_erlang_state{}) ->
  ok.

%% @private
%% @doc Convert process state when code is changed
-spec(code_change(OldVsn :: term() | {down, term()}, State :: #rabbitmq_erlang_state{},
    Extra :: term()) ->
  {ok, NewState :: #rabbitmq_erlang_state{}} | {error, Reason :: term()}).
code_change(_OldVsn, State = #rabbitmq_erlang_state{}, _Extra) ->
  {ok, State}.

%%%===================================================================
%%% Internal functions
%%%===================================================================
