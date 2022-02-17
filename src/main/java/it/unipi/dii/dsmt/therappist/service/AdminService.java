package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService {

    @Autowired
    TherapistRepository repository;

    public ArrayList<TherapistDTO> getTherapistsByState(String state) {

        ArrayList<Therapist> therapists = repository.findAllByState(state);
        ArrayList<TherapistDTO> pendingTherapists = new ArrayList<>();
        for(Therapist therapist : therapists)
            pendingTherapists.add(UsersConverter.mapTherapistDTO(therapist));


        return pendingTherapists;

    }

    public void changeStateTherapist(TherapistDTO toActivate,String state) {

        toActivate.setState(state);
        repository.save(UsersConverter.mapTherapist(toActivate));
    }

    public void declineNewTherapist(String therapist) {
        //remove therapist from db since it was declined
        repository.delete(repository.findByUsername(therapist));
    }
}
