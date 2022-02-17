package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

//Controller that ends the active session and redirects to the login page
@Controller
public class LogoutController {

    @Autowired
    private LogoutService service;

    @GetMapping("/logout")

    public String logoutGet(HttpSession session){
        service.logout(((UserDTO)session.getAttribute("user")).getUsername());
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutPost(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
