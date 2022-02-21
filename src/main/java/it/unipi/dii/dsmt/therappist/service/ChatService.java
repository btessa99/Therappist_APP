package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.Utils.ServiceUtils;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {

    public ArrayList<MessageDTO> startListener(UserDTO user, String chatter) {
        return ServiceUtils.startListener(user, chatter);
    }
}
