package it.unipi.dii.dsmt.therappist.dto;

import java.io.Serializable;
import java.util.Date;

public class TherapistDTO extends UserDTO implements Serializable {

    private int available;
    private String state;
    private String specialization1;
    private String specialization2;
    private String specialization3;
    private String gender;
    private String biography;
    private int maxPatients;
    private int acceptedPatients;

    public TherapistDTO(String fullName, String username, String email, String password, Date dateOfBirth, int available, String state, String specialization1, String specialization2, String specialization3, String gender, String biography) {
        super(fullName, username, email, password, dateOfBirth);
        this.available = available;
        this.state = state;
        this.specialization1 = specialization1;
        this.specialization2 = specialization2;
        this.specialization3 = specialization3;
        this.gender = gender;
        this.biography = biography;
    }

    public TherapistDTO(){
        super();
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSpecialization1() {
        return specialization1;
    }

    public void setSpecialization1(String specialization1) {
        this.specialization1 = specialization1;
    }

    public String getSpecialization2() {
        return specialization2;
    }

    public void setSpecialization2(String specialization2) {
        this.specialization2 = specialization2;
    }

    public String getSpecialization3() {
        return specialization3;
    }

    public void setSpecialization3(String specialization3) {
        this.specialization3 = specialization3;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public int getAcceptedPatients() {
        return acceptedPatients;
    }

    public void setAcceptedPatients(int acceptedPatients) {
        this.acceptedPatients = acceptedPatients;
    }
}
