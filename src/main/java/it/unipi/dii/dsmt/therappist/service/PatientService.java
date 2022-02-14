package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.controller.MessageController;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    public void startListener(UserDTO user){
        //Start erlang rabbitmq queue
        //New thread which awaits messages
    }
}
