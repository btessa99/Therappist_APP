package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.service.ChangeCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ChangeCredentialsController {

    @Autowired
    private ChangeCredentialsService service;

    @GetMapping(value = "/change-credentials")
    public String getPage(){
        System.out.println("ciaooo");
        return "change-credentials";
    }

    //If the new password is submitted and matches with the confirmation field,
    //and/or the issue is modified, I update the patient either in DB and in session
    @PostMapping(value = "/change-credentials")
    public String postPage(ModelMap model, @RequestParam String pass, @RequestParam String pass_confirm, @RequestParam String fields, HttpSession session){
        PatientDTO patient = (PatientDTO) session.getAttribute("user");
        boolean isChanged = false;

        //Password verification
        if(!pass.equals("")){
            if(!pass.equals(pass_confirm)){
                model.put("errorMessage", "Password don't match");
                return "change-credentials";
            }
            patient.setPassword(pass);
            isChanged = true;
        }
        //New issue verification
        if(!fields.equals(patient.getIssue())) {
            patient.setIssue(fields);
            isChanged = true;
        }
        //Update operations
        if(isChanged) {
            service.updatePatient(patient);
            session.setAttribute("user", patient);
        }

        return "redirect:/patient-page";
    }

}
