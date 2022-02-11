package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.SignInTherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SignInTherapistController {

    @Autowired
    SignInTherapistService service;

    @GetMapping(value="/sign-in-therapist")
    public String show(){
        return "sign-in-therapist";
    }

    @PostMapping(value="/sign-in-therapist")
    public String answer(ModelMap model, HttpSession session,
                         @RequestParam String username,
                         @RequestParam String email,
                         @RequestParam String full_name,
                         @RequestParam String date_of_birth,
                         @RequestParam String gender,
                         @RequestParam String pass,
                         @RequestParam String phone_number,
                         @RequestParam String biography,
                         @RequestParam Integer number,
                         @RequestParam("field") String[] fields){

        //Username and email uniqueness check
        if(!service.checkUniqueUsername(username)){

            System.out.println("error");
            model.put("errorMessage","Username already in use");
            return "sign-in";
        }

        if(!service.checkUniqueEmail(email)){

            System.out.println("error mail ");
            model.put("errorMessage","Email already in use");
            return "sign-in";
        }

        //Insertion of the therapist in the DB
        TherapistDTO newTherapist = new TherapistDTO();
        newTherapist.setUsername(username);
        newTherapist.setPassword(pass);
        newTherapist.setEmail(email);
        newTherapist.setFullName(full_name);
        newTherapist.setBiography(biography);
        newTherapist.setGender(gender);
        newTherapist.setMaxPatients(number);
        newTherapist.setAcceptedPatients(0);
        newTherapist.setState("pending");
        newTherapist.setSpecialization1(fields[0]);
        newTherapist.setSpecialization2(fields.length > 1 ? fields[1] : null);
        newTherapist.setSpecialization3(fields.length > 2 ? fields[2] : null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = formatter.parse(date_of_birth);
            newTherapist.setDateOfBirth(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        service.addTherapist(newTherapist);
        session.setAttribute("user",newTherapist);

        return "waiting-page";
    }
}
