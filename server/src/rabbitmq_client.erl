%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 12. feb 2022 17:32
%%%-------------------------------------------------------------------
-module(rabbitmq_client).
-author("benedettatessa+pietrotempesti").

-behaviour(gen_server).
-include_lib("../_build/default/lib/amqp_client/include/amqp_client.hrl").
-include_lib("eunit/include/eunit.hrl").

%% API
-export([start/0, stop/0]).

%% gen_server callbacks
-export([init/1, handle_call/3, handle_cast/2, handle_info/2, terminate/2,
  code_change/3]).

-define(SERVER, ?MODULE).

-record(rabbitmq_erlang_state, {}).

%%%===================================================================
%%% API
%%%===================================================================

%% @doc Spawns the server and registers the local name (unique)
start() ->
  gen_server:start({local, rabbit_server}, ?MODULE, [rabbit_server], []).

%% @doc Stops the rabbitmq server
stop() ->
  gen_server:cast(rabbit_server, stop).

%% @doc request for consuming
%% @param Receiver: the username and Pid of the receiver
request_consuming(Receiver_Username, Receiver_Pid)->
  gen_server:call(rabbit_server, {start_consumer, Receiver_Username, Receiver_Pid}).



%%%===================================================================
%%% gen_server callbacks
%%%===================================================================

%% @private
%% @doc Initializes the server

init([rabbit_server]) ->
  Connection = create_connection(),
  {ok, {[Connection], [], []}}.

%% @private
%% @doc Starts the consumer assigning connection, channel and queue
%% @params Receiver name and Pid, list of connections (always 1), list of active channels and consumers
%% @returns updated lists of connections, channels and consumers
handle_call({start_consumer, Receiver_Name, Receiver_Pid}, _From, {Connections, Channels, Consumers}) ->
  {New_Connections, New_Channels, New_Consumers} = consume({Connections, Channels, Consumers}, {Receiver_Name, Receiver_Pid}),
  {reply, consumer_created, {New_Connections, New_Channels, New_Consumers}}.

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

%% @private
%% @doc creates a reabbitmq tcp connection
create_connection() ->
  {ok, Connection} = amqp_connection:start(#amqp_params_network{ host="localhost"}),
  io:format("New Connection created: ~p~n", [Connection]),
  Connection.

%% @private
%% @doc gets the connection, opens a new channel for the user and starts the operations for consuming messages
%% @returns updated lists of connections, channels and consumers
consume({Connections, Channels, Consumers}, {Receiver_Username, Receiver_Pid}) ->
  New_Connections = get_connection(Connections),
  {ok, Channel} = amqp_connection:open_channel(lists:nth(1, New_Connections)),
  {ok, New_Consumer} = start_consuming_handler(Channel, {Receiver_Username, Receiver_Pid}),
  io:format("consumer new status: ~p~n", [{New_Connections, Channels ++ [{Channel, Receiver_Username}], Consumers ++ [{New_Consumer, Receiver_Username}]}]),
  {New_Connections, Channels ++ [{Channel, Receiver_Username}], Consumers ++ [{New_Consumer, Receiver_Username}]}. %return the new state


%% @private
%% @doc pick the only connection in the list and check if is alive, if not, create a new connection and update the list
%% @returns the updated list of connections
get_connection(Connections) ->
  Connection = lists:nth(1, Connections),
  case is_process_alive(Connection) of
    false ->
      New_Connection = create_connection(),
      lists:delete(Connection, Connections),
      Connections ++ [New_Connection],
      Connections;
    true ->
      Connections
  end.

%% @private
%% @doc initialize the consumer
%% @returns pid of the consumer
start_consuming_handler(Channel, {Receiver_Username, Receiver_Pid})->
  create_queue(Channel, Receiver_Username),
  Pid = spawn(fun() -> loop_consuming(Channel, {Receiver_Username, Receiver_Pid}) end),
  amqp_channel:subscribe(Channel, #'basic.consume'{queue = list_to_binary(Receiver_Username)}, Pid),
  io:format("consuming started over pid: ~p~n", [Pid]),
  {ok, Pid}.


%% @private
%% @doc create the queue for the specific user
create_queue(Channel, Queue_Name) ->
  Declare = #'queue.declare'{
    queue = list_to_binary(Queue_Name),
    durable = true
  },
  #'queue.declare_ok'{} = amqp_channel:call(Channel, Declare).

%% @private
%% @doc loop for the consumer: if alive waits for incoming messages, if receives a new message it sends this to the application Pid

loop_consuming(Channel,  {Receiver_Username, Receiver_Pid}) ->
  case is_process_alive(Channel) of
    true->
      receive
        #'basic.consume_ok'{} ->
          io:format("basic.consume_ok~n"),
          loop_consuming(Channel, {Receiver_Username, Receiver_Pid});

        #'basic.cancel_ok'{} ->
          io:format("cancel~n"),
          cancel;

        {#'basic.deliver'{delivery_tag = Tag}, {amqp_msg,_, Msg}} ->
          amqp_channel:cast(Channel, #'basic.ack'{delivery_tag = Tag}),
          io:format("this is consumed msg ~p~n", [decode_map(jsx:decode(Msg))]),
          Receiver_Pid ! decode_map(jsx:decode(Msg)),
          loop_consuming(Channel, {Receiver_Username, Receiver_Pid});

        terminate ->
          io:format("terminated~n"),
          terminate;

        _ ->
          loop_consuming(Channel, {Receiver_Username, Receiver_Pid})

      after 300000 ->
        %terminate current Receiver session by sending terminate atom
        terminate_consuming_session(Receiver_Username),
        %request for creating another pulling consumer for the current Receiver
        request_consuming(Receiver_Username, Receiver_Pid)
      end;

    false ->
      io:format("dead channel~n"),
      dead_channel
  end.