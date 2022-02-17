package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping(value="/admin-page")
    public String getAdmin(HttpSession session, ModelMap model){
        // retrieve pending therapists and add them in session
        ArrayList<TherapistDTO> pendingTherapists = service.getPendingTherapists();
        session.setAttribute("pendingTherapists", pendingTherapists);
        return "admin-page";
    }

    @PostMapping(value="/admin-page")
    public String postAdmin(HttpSession session, @RequestParam String therapist){
        //Retrieve the list of the pending therapists
        ArrayList<TherapistDTO> pendings = (ArrayList<TherapistDTO>) session.getAttribute("pendingTherapists");
        for(TherapistDTO toActivate: pendings){
            // select the therapist I want to activate and remove them from the list
            if(toActivate.getUsername().equals(therapist)){
                service.activateTherapist(toActivate);
                pendings.remove(toActivate);
                break;
            }
        }
        return "admin-page";
    }
}
