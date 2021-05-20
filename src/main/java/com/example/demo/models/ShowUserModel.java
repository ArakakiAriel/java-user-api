package com.example.demo.models;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

public class ShowUserModel {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String status;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private Timestamp updatedAt;

    public ShowUserModel(UserModel user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.lastLogin = user.getLastLogin();
        this.updatedAt = user.getUpdatedAt();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}
