package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    PatientRepository patientRepository;

    public void addPatient(PatientDTO newPatient) {

        patientRepository.save(UsersConverter.mapPatient(newPatient));

    }

    public boolean checkUniqueUsername(String username) {

        Patient unique = patientRepository.findByUsername(username);
        return unique == null;
    }

    public boolean checkUniqueEmail(String email) {

        Patient unique = patientRepository.findByEmail(email);
        return unique == null;

    }


}
