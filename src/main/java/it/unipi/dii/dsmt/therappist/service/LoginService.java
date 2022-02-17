package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TherapistRepository therapistRepository;

    //gets a patient given a username
    public PatientDTO getPatient(String userid){
        Patient patient = patientRepository.findByUsername(userid);
        return UsersConverter.mapPatientDTO(patient);
    }

    //gets a therapist given a username
    public TherapistDTO getTherapist(String userid){
        Therapist therapist = therapistRepository.findByUsername(userid);
        return UsersConverter.mapTherapistDTO(therapist);
    }

}
