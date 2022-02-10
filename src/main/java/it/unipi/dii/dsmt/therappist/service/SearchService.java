package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.service.ImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchService {

    @Autowired
    private TherapistRepository therapistRepository;

    public ArrayList<TherapistDTO> getAvailableTherapists(String specialization){
        ArrayList<Therapist> therapists = therapistRepository.findAvailableTherapists(specialization);
        ArrayList<TherapistDTO> output = new ArrayList<>();
        for(Therapist therapist: therapists){
            output.add(ImplementationService.mapTherapistDTO(therapist));
        }
        return output;
    }
}
