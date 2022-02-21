package it.unipi.dii.dsmt.therappist.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PatientDTO extends UserDTO implements Serializable {

    String issue;
    String therapist;

    public PatientDTO(String fullName, String username, String email, String password, String issue, Date dateOfBirth, String therapist) {
        super(fullName, username, email, password, dateOfBirth);
        this.issue = issue;
        this.therapist = therapist;
    }

    public PatientDTO() {
        super();
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }


    public String getTherapist() {
        return therapist;
    }

    public void setTherapist(String therapist) {
        this.therapist = therapist;
    }
}
