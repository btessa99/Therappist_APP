%%%-------------------------------------------------------------------
%%% @copyright (C) 2022, TherAPPist
%%% @doc
%%%
%%% @end
%%% Created : 12. feb 2022 20:06
%%%-------------------------------------------------------------------
-module(startup).

%% API
-export([]).

init_startup() ->
  mnesiaManager:init(),
  therappist_server:start_link(),
  Serv_pid = spawn(fun() -> startup_loop() end),
  register('therappist_server', Serv_pid),
  Serv_pid.

startup_loop() ->
  receive
    {From, Action, Argument} -> spawn(fun() -> From ! therappist_server:call_server({Action, Argument}) end);
    _ -> ok
  end,
  startup_loop().

