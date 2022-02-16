%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 12. feb 2022 20:06
%%%-------------------------------------------------------------------
-module(startup).

%% API
-export([init_startup/0]).

init_startup() ->
  mnesiaHandler:init(),
  server_therappist:start(),
  Serv = spawn(fun() -> startup_loop() end),
  io:format("I am ~w", [Serv]),
  unregister(therappist_server),
  register(therappist_server, Serv),
  Serv.

startup_loop() ->
  receive
    {From, Action, Argument} -> spawn(fun() -> From ! server_therappist:call_server({Action, Argument}) end);
    _ -> ok
  end,
  startup_loop().



