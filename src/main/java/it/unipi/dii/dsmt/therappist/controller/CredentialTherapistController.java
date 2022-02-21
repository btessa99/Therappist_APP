package it.unipi.dii.dsmt.therappist.controller;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.ChangeCredentialsService;
import it.unipi.dii.dsmt.therappist.service.CredentialTherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class CredentialTherapistController {

    @Autowired
    private CredentialTherapistService service;

    @GetMapping(value = "/change-credentials-therapists")
    public String getPage() {
        return "change-credentials-therapists";
    }

    //If the new password is submitted and matches with the confirmation field,
    //and/or the issue is modified, I update the patient either in DB and in session
    @PostMapping(value = "/change-credentials-therapists")
    public String postPage(ModelMap model, @RequestParam String pass, @RequestParam String pass_confirm, @RequestParam("field") String[] fields, @RequestParam String change, @RequestParam Integer number, HttpSession session) {
        TherapistDTO therapist = (TherapistDTO) session.getAttribute("user");
        boolean isChanged = false;

        //Password verification
        if (!pass.equals("")) {
            if (!pass.equals(pass_confirm)) {
                model.put("errorMessage", "Password don't match");
                return "change-credentials-therapist";
            }
            therapist.setPassword(pass);
            isChanged = true;
        }

        String[] mySpecializations = new String[]{therapist.getSpecialization1(), therapist.getSpecialization2(), therapist.getSpecialization3()};
        if (!Arrays.equals(mySpecializations, fields)) {

            isChanged = true;
            therapist.setSpecialization1(fields[0]);
            therapist.setSpecialization2(fields.length > 1 ? fields[1] : null);
            therapist.setSpecialization3(fields.length > 2 ? fields[2] : null);
        }

        if (therapist.getMaxPatients() != number) {

            isChanged = true;
            therapist.setMaxPatients(number);
        }

        if (!therapist.getState().equals(change)) {

            isChanged = true;
            therapist.setState(change);
        }

        //Update operations
        if (isChanged) { //not access to the db unless a parameter changed
            service.updateTherapist(therapist);
            session.setAttribute("user", therapist);
        }

        return "redirect:/therapist-page";
    }

}
