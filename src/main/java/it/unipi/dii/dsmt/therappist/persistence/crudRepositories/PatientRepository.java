package it.unipi.dii.dsmt.therappist.persistence.crudRepositories;

import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface PatientRepository extends CrudRepository<Patient, String> {

    Patient findByUsername(String Id);

    ArrayList<Patient> findByTherapist(String therapist);

    Patient findByEmail(String email);
}
