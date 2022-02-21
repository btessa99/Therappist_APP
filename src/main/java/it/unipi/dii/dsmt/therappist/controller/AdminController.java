package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping(value = "/admin-page")
    public String getAdmin(HttpSession session, ModelMap model) {
        // retrieve pending therapists and add them in session
        ArrayList<TherapistDTO> pendingTherapists = service.getTherapistsByState("pending");
        ArrayList<TherapistDTO> activeTherapists = service.getTherapistsByState("active");
        session.setAttribute("pendingTherapists", pendingTherapists);
        session.setAttribute("activeTherapists", activeTherapists);
        return "admin-page";
    }

    @PostMapping(value = "/admin-page")
    public String postAdmin(HttpSession session, HttpServletRequest request) {
        //Retrieve the list of the pending therapists


        if (request.getParameter("therapist") != null) {

            String newState = request.getParameter("state");
            String therapist = request.getParameter("therapist");

            ArrayList<TherapistDTO> pendings = (ArrayList<TherapistDTO>) session.getAttribute("pendingTherapists");

            for (TherapistDTO pending : pendings) {
                // select the therapist I want to activate and remove them from the list
                if (pending.getUsername().equals(therapist)) {

                    if (newState.equals("active")) { //add the new therapist to the list of active therapists
                        service.changeStateTherapist(pending, "active");
                        ArrayList<TherapistDTO> actives = (ArrayList<TherapistDTO>) session.getAttribute("activeTherapists");
                        actives.add(pending);
                        session.setAttribute("activeTherapists", actives);
                    } else
                        service.declineNewTherapist(therapist); //this therapist failed the interview. Remove from database

                    pendings.remove(pending);
                    break;
                }
            }
        } else {
            String therapist = request.getParameter("therapistActive");
            ArrayList<TherapistDTO> active = (ArrayList<TherapistDTO>) session.getAttribute("activeTherapists");
            for (TherapistDTO toActivate : active) {
                // select the therapist I want to activate and remove them from the list
                if (toActivate.getUsername().equals(therapist)) {
                    service.changeStateTherapist(toActivate, "admin");
                    active.remove(toActivate);
                    break;
                }
            }
        }
        return "admin-page";
    }
}
