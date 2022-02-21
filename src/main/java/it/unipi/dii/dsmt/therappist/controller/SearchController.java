package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class SearchController {

    @Autowired
    SearchService service;

    //Set the suitable therapist for the patient's issue
    @GetMapping(value = "/search-page")
    public String getPatient(ModelMap model, HttpSession session) {
        PatientDTO patient = (PatientDTO) session.getAttribute("user");
        ArrayList<TherapistDTO> therapists = service.getAvailableTherapists(patient.getIssue());
        model.addAttribute("availableTherapists", therapists);
        return "search-page";
    }

    @PostMapping(value = "/search-page")
    public String postPatient(HttpSession session, @RequestParam String therapist) {

        System.out.println(therapist);
        PatientDTO myself = (PatientDTO) session.getAttribute("user");
        myself.setTherapist(therapist); // therapist no longer null

        service.associateTherapist(myself); //save info to db

        //decrement therapist availability
        service.updateTherapist(therapist);

        //update session user
        session.setAttribute("user", myself);

        return "redirect:/patient-page";
    }
}
