package it.unipi.dii.dsmt.therappist.controller;


import it.unipi.dii.dsmt.therappist.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @Autowired
    ChatService service;

    @GetMapping(value = "/chat-page")
    public String init(ModelMap model, HttpSession session){

        return "chat-page";
    }

    @PostMapping(value = "/chat-page")
    public String postInit(ModelMap model, HttpSession session){

        return "chat-page";
    }


}
