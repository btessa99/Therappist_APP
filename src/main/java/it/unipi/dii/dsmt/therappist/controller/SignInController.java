package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class SignInController {

    @Autowired
    SignInService service;

    @GetMapping(value="/sign-in")
    public String showForm(){
        return "sign-in";
    }

    @PostMapping(value="/sign-in")
    public void seeAnswers(ModelMap model, @RequestParam String first_name,
                                            @RequestParam String last_name,
                                            @RequestParam String email,
                                            @RequestParam String username,
                                            @RequestParam String pass,
                                            @RequestParam String pass_confirm,
                                            @RequestParam String fields){
        System.out.println(fields);
    }
}
