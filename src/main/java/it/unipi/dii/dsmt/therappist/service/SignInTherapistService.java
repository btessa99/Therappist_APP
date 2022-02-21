package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInTherapistService {

    @Autowired
    TherapistRepository therapistRepository;

    public void addTherapist(TherapistDTO newTherapist) {

        therapistRepository.save(UsersConverter.mapTherapist(newTherapist));

    }

    public boolean checkUniqueUsername(String username) {

        Therapist unique = therapistRepository.findByUsername(username);
        return unique == null;
    }

    public boolean checkUniqueEmail(String email) {

        Therapist unique = therapistRepository.findByEmail(email);
        return unique == null;

    }
}
