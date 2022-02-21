package it.unipi.dii.dsmt.therappist.dto;

import java.io.Serializable;
import java.util.Date;

public abstract class UserDTO implements Serializable {

    private String fullName;
    private String username;
    private String email;
    private String password;
    private Date dateOfBirth;

    public UserDTO(String fullName, String username, String email, String password, Date dateOfBirth) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public UserDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
