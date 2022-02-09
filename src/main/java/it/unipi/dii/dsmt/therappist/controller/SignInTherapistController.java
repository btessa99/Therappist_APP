package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.service.SignInTherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInTherapistController {

    @Autowired
    SignInTherapistService service;

    @GetMapping(value="/sign-in-therapist")
    public String show(){
        return "sign-in-therapist";
    }

    @PostMapping(value="/sign-in-therapist")
    public String answer(@RequestParam("field") String[] fields){

        System.out.println(fields[0]);

        return "waiting-page";
    }
}
