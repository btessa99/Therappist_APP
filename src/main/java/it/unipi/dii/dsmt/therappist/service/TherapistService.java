package it.unipi.dii.dsmt.therappist.service;

import it.unipi.dii.dsmt.therappist.Utils.ServiceUtils;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.PatientDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.persistence.crudRepositories.PatientRepository;
import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.persistence_service.UsersConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TherapistService {

    @Autowired
    PatientRepository repository;

    public ArrayList<PatientDTO> getMyPatients(String myUsername){

        ArrayList<Patient> patients = repository.findByTherapist(myUsername);
        ArrayList<PatientDTO> myPatients = new ArrayList<>();

        for(Patient myPatient: patients)
            myPatients.add(UsersConverter.mapPatientDTO(myPatient));

        return myPatients;


    }


    public ArrayList<MessageDTO> startListener(UserDTO user, String chatter){
        return ServiceUtils.startListener(user,chatter);
    }

}
