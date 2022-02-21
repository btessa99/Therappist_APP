package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.Utils.ConnectionUtils;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.service.TerminateSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TerminateSessionController {

    @Autowired
    TerminateSessionService service;

    @GetMapping(value = "/terminate")
    public String terminate(HttpSession session) {

        String endpoint = (String) session.getAttribute("endpoint");
        UserDTO user = (UserDTO) session.getAttribute("user");

        ConnectionUtils.endConnection(user.getUsername());
        service.dividePatientAndTherapist(user.getUsername(), endpoint);
        return "redirect:/search-page";

    }


}
