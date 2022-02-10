package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SearchController {

    @Autowired
    SearchService service;

    @GetMapping(value = "/search-page")
    public String getPatient(ModelMap model, HttpSession session){
        model.addAttribute("controller", "search");
        PatientDTO patient = (PatientDTO) session.getAttribute("user");
        ArrayList<TherapistDTO> therapists = service.getAvailableTherapists(patient.getIssue());
        model.addAttribute("availableTherapists", therapists);
        return "search-page";
    }

    @PostMapping(value = "/search-page")
    public String postPatient(SessionAttributeStore store, WebRequest request){
        System.out.println("post");

        return "search-page";
    }
}
