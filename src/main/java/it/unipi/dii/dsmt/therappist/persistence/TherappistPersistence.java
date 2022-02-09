package it.unipi.dii.dsmt.therappist.persistence;


import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.TherapistRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import it.unipi.dii.dsmt.therappist.persistence.service.ImplementationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TherappistPersistence {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TherapistRepository therapistRepository;

    public ArrayList<TherapistDTO> findTherapistBySpecialization(String specialization) {
        ArrayList<Therapist> therapists = therapistRepository.findAllBySpecialization1OrSpecialization2OrSpecialization3(specialization, specialization, specialization);
        ArrayList<TherapistDTO> dto = new ArrayList<>();
        if(therapists != null)
            for(Therapist t: therapists)
                dto.add(ImplementationService.mapTherapistDTO(t));
        return dto;
    }

    public TherapistDTO findTherapistByUsername(String username) {
        return ImplementationService.mapTherapistDTO(therapistRepository.findByUsername(username));
    }

    public PatientDTO findPatientByUsername(String username) {
        return ImplementationService.mapPatientDTO(patientRepository.findByUsername(username));
    }

    public ArrayList<PatientDTO> findPatientsByTherapist(String therapist) {
        ArrayList<Patient> patients = patientRepository.findByTherapist(therapist);
        ArrayList<PatientDTO> dto = new ArrayList<>();
        if(patients != null)
            for(Patient p: patients)
                dto.add(ImplementationService.mapPatientDTO(p));
        return dto;
    }

    public boolean saveOrUpdatePatient(PatientDTO patient) {
        Patient p = patientRepository.save(ImplementationService.mapPatient(patient));
        return (p != null);
    }

    public boolean saveOrUpdateTherapist(TherapistDTO therapist) {
        Therapist t = therapistRepository.save(ImplementationService.mapTherapist(therapist));
        return (t != null);
    }

    public void deletePatient(PatientDTO patient) {
        patientRepository.delete(ImplementationService.mapPatient(patient));
    }

    public void deleteTherapist(TherapistDTO therapist) {
        therapistRepository.delete(ImplementationService.mapTherapist(therapist));
    }
}
