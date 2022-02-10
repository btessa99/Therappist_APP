package it.unipi.dii.dsmt.therappist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeCredentialsController {


    @GetMapping(value = "/change-credentials")
    public String getTherapist(){
        return "change-credentials";
    }

    @PostMapping(value = "/change-credentials")
    public String postTherapist(){
        return "change-credentials";
    }

}
