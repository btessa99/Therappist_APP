package it.unipi.dii.dsmt.therappist.Utils;

import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;
import java.util.HashMap;


public  class ConnectionUtils {

   static HashMap<String,ErlangConnection> connections = new HashMap<>();

   public static void setNewConnection(String userId,ErlangConnection userConnection){

       connections.put(userId,userConnection);
   }

   public static ErlangConnection getConnection(String userId){
       return connections.get(userId);
   }
}
