/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiuser.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="userAddresses")
@Table(name="userAddresses")
public class UserAddressModel {

    @Id //to create primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_address_id", unique = true, nullable = false, updatable = false) //Column details
    String userAddressId;
    //@Column(name = "user_id", nullable = false, updatable = false) //Column details
    //private String userId;
    
    @Column(name = "postal_code", nullable = false)
    private int postalCode;
    @Column(name ="address_1", nullable = false)
    private String address1;
    @Column(name ="address_2")
    private String address2;
    @Column(name ="city", nullable = false)
    private String city;
    @Column(name ="state", nullable = false)
    private String state;
    @Column(name ="country", nullable = false)
    private String country;
    @Column(name ="phone_number", nullable = false)
    private String phoneNumber;
    @Column(name ="create_at")
    private Timestamp createAt;
    @Column(name ="updated_at")
    private Timestamp updatedAt;
    @Column(name ="default_address", nullable = false)
    private Boolean defaultAddress;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserModel user; 
    


    public String getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(String userAddressId) {
        this.userAddressId = userAddressId;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

   
    


    
    
    
  
    
}
