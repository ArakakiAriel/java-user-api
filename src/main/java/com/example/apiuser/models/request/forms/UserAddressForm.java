/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiuser.models.request.forms;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserAddressForm {
    
    
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

    public int getPostalCode() {
        return postalCode;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
