package it.unipi.dii.dsmt.therappist.persistence.persistence_service;


import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;

public class UsersConverter {

    public static Patient mapPatient(PatientDTO input){
        if(input == null)
            return null;
        Patient patient = new Patient();
        patient.setUsername(input.getUsername());
        patient.setEmail(input.getEmail());
        patient.setPassword(input.getPassword());
        patient.setIssue(input.getIssue());
        patient.setTherapist(input.getTherapist());
        patient.setDateOfBirth(input.getDateOfBirth());
        patient.setFullName(input.getFullName());
        return patient;
    }

    public static PatientDTO mapPatientDTO(Patient input){
        if(input == null)
            return null;
        PatientDTO patient = new PatientDTO();
        patient.setUsername(input.getUsername());
        patient.setEmail(input.getEmail());
        patient.setPassword(input.getPassword());
        patient.setIssue(input.getIssue());
        patient.setTherapist(input.getTherapist());
        patient.setDateOfBirth(input.getDateOfBirth());
        patient.setFullName(input.getFullName());
        return patient;
    }

    public static Therapist mapTherapist(TherapistDTO input){
        if(input == null)
            return null;
        Therapist therapist = new Therapist();
        therapist.setUsername(input.getUsername());
        therapist.setEmail(input.getEmail());
        therapist.setPassword(input.getPassword());
        therapist.setSpecialization1(input.getSpecialization1());
        therapist.setSpecialization2(input.getSpecialization2());
        therapist.setSpecialization3(input.getSpecialization3());
        therapist.setFullName(input.getFullName());
        therapist.setDateOfBirth(input.getDateOfBirth());
        therapist.setState(input.getState());
        therapist.setBiography(input.getBiography());
        therapist.setGender(input.getGender());
        therapist.setMaxPatients(input.getMaxPatients());
        therapist.setAcceptedPatients(input.getAcceptedPatients());
        return therapist;
    }

    public static TherapistDTO mapTherapistDTO(Therapist input){
        if(input == null)
            return null;
        TherapistDTO therapist = new TherapistDTO();
        therapist.setUsername(input.getUsername());
        therapist.setEmail(input.getEmail());
        therapist.setPassword(input.getPassword());
        therapist.setSpecialization1(input.getSpecialization1());
        therapist.setSpecialization2(input.getSpecialization2());
        therapist.setSpecialization3(input.getSpecialization3());
        therapist.setFullName(input.getFullName());
        therapist.setDateOfBirth(input.getDateOfBirth());
        therapist.setState(input.getState());
        therapist.setBiography(input.getBiography());
        therapist.setGender(input.getGender());
        therapist.setMaxPatients(input.getMaxPatients());
        therapist.setAcceptedPatients(input.getAcceptedPatients());
        return therapist;
    }
}
