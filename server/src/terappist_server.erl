%%%-------------------------------------------------------------------
%%% @author benedettatessa
%%% @copyright (C) 2022, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 08. feb 2022 23:06
%%%-------------------------------------------------------------------
-module(terappist_server).
-author("benedettatessa").

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
-spec(start_link() ->
  {ok, Pid :: pid()} | ignore | {error, Reason :: term()}).
start_link() ->
  gen_server:start_link({local, ?SERVER}, ?MODULE, [], []).

%%%===================================================================
%%% gen_server callbacks
%%%===================================================================

%% @private
%% @doc Initializes the server
-spec(init(Args :: term()) ->
  {ok, State :: #terappist_server_state{}} | {ok, State :: #terappist_server_state{}, timeout() | hibernate} |
  {stop, Reason :: term()} | ignore).
init([]) ->
  {ok, #terappist_server_state{}}.

%% @private
%% @doc Handling call messages
-spec(handle_call(Request :: term(), From :: {pid(), Tag :: term()},
    State :: #terappist_server_state{}) ->
  {reply, Reply :: term(), NewState :: #terappist_server_state{}} |
  {reply, Reply :: term(), NewState :: #terappist_server_state{}, timeout() | hibernate} |
  {noreply, NewState :: #terappist_server_state{}} |
  {noreply, NewState :: #terappist_server_state{}, timeout() | hibernate} |
  {stop, Reason :: term(), Reply :: term(), NewState :: #terappist_server_state{}} |
  {stop, Reason :: term(), NewState :: #terappist_server_state{}}).
handle_call(_Request, _From, State = #terappist_server_state{}) ->
  {reply, ok, State}.

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
