%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 12. feb 2022 21:49
%%%-------------------------------------------------------------------
-module(mnesiaHandler).
-author("benedettatessa+pietrotempesti").

%% API
-export([init/0,add_message/4,get_messages/2,retrieve_messages_of_a_therapy/2]).
-include("hrl/schema_info.hrl").
-include_lib("stdlib/include/qlc.hrl").


init() ->
  mnesia:create_schema([node()]),
  mnesia:start(),
  %%check if tables is present
  case mnesia:wait_for_tables(messages,4000) == ok of
    true-> %%if true, finish
      io:format("mnesia initialized ~n"),
      ok;
    false -> %%else, create table
      io:format("mnesia initialized ~n"),
      mnesia:create_table(messages,
      [{attributes, record_info(fields, messages)},
      {disc_copies, [node()]}
      ])

  end.

add_message(Timestamp,Sender,Receiver,Text)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#messages{
      timestamp = Timestamp,
      sender = Sender,
      receiver = Receiver,
      text = Text
    })
           end,
  mnesia:activity(transaction,Insert).

%%helper function
get_messages(Sender,Receiver)->
  Retrieve = fun() ->
    %% retrieves information about messages between two users, a sender and a receiver
    Q = qlc:q([{Sender,Receiver, E#messages.text, E#messages.timestamp} || E <- mnesia:table(messages), E#messages.sender == Sender , E#messages.receiver == Receiver]),
    qlc:e(Q) %% evaluates query and collects messages
      end,
  mnesia:transaction(Retrieve).

%%collects messages retrieved by the get_messages function
retrieve_messages_of_a_therapy(Sender,Receiver)->
  {_,History_of_Messages} = get_messages(Sender,Receiver),
  History_of_Messages.
