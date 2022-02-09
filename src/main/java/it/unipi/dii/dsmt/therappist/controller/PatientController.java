package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;


@Controller
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping(value = "/patient-page")
    public String getPatient(ModelMap model, HttpSession session){
        System.out.println("get");
        PatientDTO p = (PatientDTO) session.getAttribute("user");
        if(p != null)
            System.out.print(p.getFullName() + " is dealing with " + p.getIssue());
        else
            System.out.println("NULL");
        return "patient-page";
    }

    @PostMapping(value = "/patient-page")
    public String postPatient(SessionAttributeStore store, WebRequest request){
        System.out.println("post");

        return "patient-page";
    }
}
