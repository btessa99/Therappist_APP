package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TherapistController {

    @Autowired
    private TherapistService service;


    @GetMapping(value = "/therapist-page")
    public String getTherapist(){
        return "therapist-page";
    }

    @PostMapping(value = "/therapist-page")
    public String postTherapist(){
        return "therapist-page";
    }


}
