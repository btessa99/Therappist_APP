package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class TherapistController {

    @Autowired
    private TherapistService service;


    @GetMapping(value = "/therapist-page")
    public String getTherapist(ModelMap model,HttpSession session){
        session.setAttribute("role","therapist");
        TherapistDTO myself = (TherapistDTO) session.getAttribute("user");
        //get patients associated to this therapist
        ArrayList<PatientDTO> myPatients = service.getMyPatients(myself.getUsername());
        model.addAttribute("myPatients",myPatients);

        return "therapist-page";
    }

    @PostMapping(value = "/therapist-page")
    public String postTherapist(ModelMap model, HttpSession session, @RequestParam String patient){
        session.setAttribute("endpoint",patient);
        if(!(boolean)session.getAttribute("activeListener")){
            ArrayList<MessageDTO> history = service.startListener((TherapistDTO) session.getAttribute("user"), patient);
            session.setAttribute("history", history);
            session.setAttribute("activeListener", true);
        }
        return "redirect:/chat-page";
    }


}
