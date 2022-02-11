package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;


@Controller
//@SessionAttributes("user")
public class LoginController {

    @Autowired
    LoginService service;

    @GetMapping(value="/")
    public String showLoginPage(HttpSession session, SessionStatus status){
        return "welcome-page";
    }

    @PostMapping(value="/")
    public String showWelcomePage(ModelMap model, HttpSession session, @RequestParam String name, @RequestParam String password, @RequestParam String role){

        UserDTO user = null;
        
        if(role.equals("Patient")) 
            user = service.getPatient(name);
        else
            user = service.getTherapist(name);

        //Error handling
        if(user == null){
            model.put("errorMessage", "Username not found");
            return "welcome-page";
        }
        if(!user.getPassword().equals(password)){
            model.put("errorMessage", "Wrong password");
            return "welcome-page";            
        }

        //User found: login
        session.setAttribute("user",user);

        if(role.equals("Patient")){
            PatientDTO logged = (PatientDTO) user;

            if(logged.getTherapist() == null)
                return "redirect:/search-page"; // the patient needs to look for a therapist
            
            return "redirect:/patient-page"; //the patient has been assigned to a therapist
                
                
        }
        // the user credentials are valid and the user in question is a therapist
        TherapistDTO loggedTherapist = (TherapistDTO) user;
        if(loggedTherapist.getState().equals("pending")) //the therapist is yet to be admitted
            return "waiting-page";

        return "redirect:/therapist-page"; //the therapist can go to its personal page

    }
    


}