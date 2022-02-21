package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminateSessionService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TherapistRepository therapistRepository;

    public void dividePatientAndTherapist(String patientUsername, String therapistUsername) {

        Patient patient = patientRepository.findByUsername(patientUsername);
        patient.setTherapist(null);
        patientRepository.save(patient);

        Therapist therapist = therapistRepository.findByUsername(therapistUsername);
        int numPatients = therapist.getAcceptedPatients();
        therapist.setAcceptedPatients(--numPatients);
        therapistRepository.save(therapist);

    }
}
