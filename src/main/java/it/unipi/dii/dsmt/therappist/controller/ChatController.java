package it.unipi.dii.dsmt.therappist.controller;


import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.service.ChatService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ChatController {

    @Autowired
    ChatService service;

    @GetMapping(value = "/chat-page")
    public String init(ModelMap model, HttpSession session) {
        ArrayList<MessageDTO> history = service.startListener((UserDTO) session.getAttribute("user"), (String) session.getAttribute("endpoint"));
        session.setAttribute("history", history);
        System.out.println("hellou");
        return "chat-page";
    }

    @PostMapping(value = "/chat-page")
    public String postInit(ModelMap model, HttpSession session) {

        return "chat-page";
    }


}
