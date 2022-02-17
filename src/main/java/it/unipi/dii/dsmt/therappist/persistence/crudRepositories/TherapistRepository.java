package it.unipi.dii.dsmt.therappist.persistence.crudRepositories;


import it.unipi.dii.dsmt.therappist.persistence.entities.Patient;
import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TherapistRepository extends CrudRepository<Therapist, String> {
    Therapist findByUsername(String username);

    @Query("select t from Therapist t where (t.specialization1 = ?1 or t.specialization2 = ?1 or t.specialization3 = ?1) and (t.state = 'active' and t.acceptedPatients < t.maxPatients)")
    ArrayList<Therapist> findAvailableTherapists(String specialization);

    Therapist findByEmail(String email);

    ArrayList<Therapist> findAllByState(String state);

}
