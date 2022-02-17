package it.unipi.dii.dsmt.therappist.Utils;

import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;

import java.util.ArrayList;

public class ServiceUtils {

    public static ArrayList<MessageDTO> startListener(UserDTO user, String chatter){
        ErlangConnection connection;
        boolean isUp = ConnectionUtils.isConnected(user.getUsername());
        if(isUp)
            connection = ConnectionUtils.getConnection(user.getUsername());
        else
            connection = new ErlangConnection();
        ArrayList<MessageDTO> history = connection.initialize(user, chatter);
        if(!isUp)
            ConnectionUtils.setNewConnection(user.getUsername(),connection);
        return history;
    }

}
