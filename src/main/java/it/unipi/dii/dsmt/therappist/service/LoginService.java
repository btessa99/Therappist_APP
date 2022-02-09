package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.service.ImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TherapistRepository therapistRepository;




    public PatientDTO getPatient(String userid){
        Patient patient = patientRepository.findByUsername(userid);
        return ImplementationService.mapPatientDTO(patient);
    }

    public TherapistDTO getTherapist(String userid){
        Therapist therapist = therapistRepository.findByUsername(userid);
        return ImplementationService.mapTherapistDTO(therapist);
    }



}
