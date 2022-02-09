package it.unipi.dii.dsmt.therappist.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "therapist")
public class Therapist implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "spacialization1")
    private String specialization1;

    @Column(name = "spacialization2")
    private String specialization2;

    @Column(name = "spacialization3")
    private String specialization3;

    @Column(name = "state")
    private String state;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "biography")
    private String biography;

    public Therapist(){}

    public Therapist(String username, String fullName, String email, String password, String specialization1, String specialization2, String specialization3, String state) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.specialization1 = specialization1;
        this.specialization2 = specialization2;
        this.specialization3 = specialization3;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
}
