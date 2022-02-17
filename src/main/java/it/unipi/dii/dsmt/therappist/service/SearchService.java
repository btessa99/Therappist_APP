package it.unipi.dii.dsmt.therappist.service;


import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchService {

    @Autowired
    private TherapistRepository therapistRepository;
    @Autowired
    private PatientRepository patientRepository;

    //gets therapist who are specialized in a certain field and have
    //free spots
    public ArrayList<TherapistDTO> getAvailableTherapists(String specialization){
        ArrayList<Therapist> therapists = therapistRepository.findAvailableTherapists(specialization);
        ArrayList<TherapistDTO> output = new ArrayList<>();
        for(Therapist therapist: therapists){
            output.add(UsersConverter.mapTherapistDTO(therapist));
        }
        return output;
    }

    //the patient has now the therapist field updated
    //that means they have chosen a therapist and we need
    //to update such info in the database
    public void associateTherapist(PatientDTO patient){

        patientRepository.save(UsersConverter.mapPatient(patient));

    }

    //increments the number of the patients accepted by a therapist
    public void updateTherapist(String username){

        Therapist therapist = therapistRepository.findByUsername(username);

        int availability = therapist.getAcceptedPatients();
        therapist.setAcceptedPatients(++availability);
        therapistRepository.save(therapist);

    }
}