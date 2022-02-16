package it.unipi.dii.dsmt.therappist.Utils;

import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public  class ConnectionUtils {

   static ConcurrentHashMap<String,ErlangConnection> connections = new ConcurrentHashMap<>();

   public static void setNewConnection(String userId,ErlangConnection userConnection){

       connections.put(userId,userConnection);
   }

   public static ErlangConnection getConnection(String userId){
       return connections.get(userId);
   }

   public static void closeConnection(String userId) {connections.remove(userId); }
}
