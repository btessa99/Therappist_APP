package it.unipi.dii.dsmt.therappist.persistence.crudRepositories;


import it.unipi.dii.dsmt.therappist.persistence.entities.Therapist;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TherapistRepository extends CrudRepository<Therapist, String> {
    Therapist findByUsername(String username);
    ArrayList<Therapist> findAllBySpecialization1OrSpecialization2OrSpecialization3(String specialization1, String specialization2, String specialization3);

}
