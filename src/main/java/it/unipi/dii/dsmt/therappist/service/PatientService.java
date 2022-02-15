package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.controller.MessageController;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    ErlangConnection connection;


    public void startListener(UserDTO user, String chatter){
        connection = new ErlangConnection();
        connection.initialize(user, chatter);
        Thread receiveThread = new Thread(new ListenerTask(), user.getUsername() + "_thread");
        receiveThread.setDaemon(true);
        receiveThread.start();
    }

    private class ListenerTask implements Runnable {

        @Override
        public void run() {

            while(!Thread.currentThread().isInterrupted()) {
                connection.receiveMessage();
            }
        }
    }
}
