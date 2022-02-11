package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@SessionAttributes("name")
public class SignInController {

    @Autowired
    SignInService service;

    @GetMapping(value="/sign-in")
    public String showForm(){
        return "sign-in";
    }

    @PostMapping(value="/sign-in")
    public String seeAnswers(ModelMap model, HttpSession session, @RequestParam String full_name,
                             @RequestParam String email,
                             @RequestParam String username,
                             @RequestParam String pass,
                             @RequestParam String date_of_birth,
                             @RequestParam String pass_confirm,
                             @RequestParam String fields){


        //Check if the username is already used for patients
        if(!service.checkUniqueUsername(username)){

            System.out.println("error");
            model.put("errorMessage","Username already in use");
            return "sign-in";
        }

        //check if the email is already used for patients
        if(!service.checkUniqueEmail(email)){

            System.out.println("error mail ");
            model.put("errorMessage","Email already in use");
            return "sign-in";
        }

        //Password and confirmation check
        if(!pass.equals(pass_confirm)){
            model.put("errorMessage","Password and confirmation don't match");
            return "sign-in";
        }

        //Insertion of the new patient and login
        PatientDTO newPatient = new PatientDTO();
        newPatient.setFullName(full_name);
        newPatient.setEmail(email);
        newPatient.setPassword(pass);
        newPatient.setUsername(username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = formatter.parse(date_of_birth);
            newPatient.setDateOfBirth(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newPatient.setIssue(fields);

        session.setAttribute("user",newPatient);
        service.addPatient(newPatient);

        return "redirect:/search-page";


    }
}
