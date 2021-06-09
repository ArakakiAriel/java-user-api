package com.example.apiuser.models;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShowUserModel {

    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String status;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private Timestamp updatedAt;
    private Set<String> roles ;

    public ShowUserModel(UserModel user) {
        this.userId = user.getUserId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.lastLogin = user.getLastLogin();
        this.updatedAt = user.getUpdatedAt();
    }

    public String getUserId() {
        return userId;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }
}
