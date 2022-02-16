package it.unipi.dii.dsmt.therappist.Utils;

import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;

public class ServiceUtils {

    public static void startListener(UserDTO user, String chatter){
        ErlangConnection connection = new ErlangConnection();
        connection.initialize(user, chatter);
        ConnectionUtils.setNewConnection(user.getUsername(),connection);
        Thread receiveThread = new Thread(new ListenerTask(connection), user.getUsername() + "_thread");
        receiveThread.setDaemon(true);
        receiveThread.start();
    }

    private static class ListenerTask implements Runnable {

        ErlangConnection connection;

        public ListenerTask(ErlangConnection connection){

            this.connection = connection;
        }

        @Override
        public void run() {

            while(!Thread.currentThread().isInterrupted()) {
                connection.receiveMessage();
            }
        }
    }
}
