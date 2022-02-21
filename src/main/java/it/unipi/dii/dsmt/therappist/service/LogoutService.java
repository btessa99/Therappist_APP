package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.Utils.ConnectionUtils;
import org.springframework.stereotype.Service;

//Empty service: I don't need this
@Service
public class LogoutService {

    public void logout(String username) {
        ConnectionUtils.endConnection(username);
    }
}
