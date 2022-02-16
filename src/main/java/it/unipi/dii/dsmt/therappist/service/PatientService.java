package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.Utils.ServiceUtils;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.erlangConnection.ErlangConnection;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    ErlangConnection connection;


    public void startListener(UserDTO user, String chatter){
        ServiceUtils.startListener(user,chatter);
    }



}
