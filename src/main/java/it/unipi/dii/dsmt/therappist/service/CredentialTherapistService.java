package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.service.ImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialTherapistService {

    @Autowired
    TherapistRepository repository;
    public void updateTherapist(TherapistDTO therapist) {

        repository.save(ImplementationService.mapTherapist(therapist));
    }
}
