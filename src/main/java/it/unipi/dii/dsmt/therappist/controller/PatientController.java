package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
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
import java.util.ArrayList;


@Controller
public class PatientController {

    @Autowired
    private PatientService service;

    // First visit to the page: opens the chat and erlang management starts:
    // Starts the message listener for the patient
    @GetMapping(value = "/patient-page")
    public String getPatient(ModelMap model, HttpSession session){
        session.setAttribute("role","patient");
        PatientDTO myself = (PatientDTO) session.getAttribute("user");
        session.setAttribute("endpoint",myself.getTherapist());
        return "redirect:/chat-page";
    }

    @PostMapping(value = "/patient-page")
    public String postPatient(SessionAttributeStore store, WebRequest request){
        return "redirect:/chat-page";
    }


}
