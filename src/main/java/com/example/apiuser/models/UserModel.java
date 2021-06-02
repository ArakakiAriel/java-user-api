package com.example.apiuser.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity(name="users")
@Table(name="users") //It creates the table with this name or it will go to update it
public class UserModel {


    @Id //to create primary key
    @Column(name = "user_id", unique = true, nullable = false, updatable = false) //Column details
    private String userId;
    @NotNull(message="(Name was not provided)")
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "(Please insert a valid email address)")
    @NotNull(message="(Email was not provided)")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message="(Password was not provided)")
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "last_login")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastLogin;
    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> userRoles = new HashSet<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<RoleModel> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleModel> userRoles) {
        this.userRoles = userRoles;
    }

}
