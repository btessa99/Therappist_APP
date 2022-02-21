package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeCredentialsService {

    @Autowired
    PatientRepository patientRepository;

    //Update a patient with modified password or issue
    public void updatePatient(PatientDTO patient) {
        patientRepository.save(UsersConverter.mapPatient(patient));
    }

}
