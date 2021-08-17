/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiuser.services;

import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.UserAddressModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.repositories.UserAddressRepository;
import com.example.apiuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.example.apiuser.utils.DateUtils.getTimestampNow;
import java.util.ArrayList;


@Service
public class UserAddressService {
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    UserRepository userRepository;
     
     

    
    public UserAddressModel getUserAddressById(String id){

        UserAddressModel userAddress =  userAddressRepository.findById(id)
                .orElse(null);


        if(userAddress == null){
            throw new DataNotFoundException("Address not found in the database");
        }

        return userAddress;
    }
    
    
    public UserAddressModel deleteAddress(String Id){
        userAddressRepository.deleteById(Id);
             
        return null;
    }



    public Object createUserAddress(UserAddressModel userAddress) {
        try{
            //Update updated_at date
            Timestamp now = getTimestampNow();
            userAddress.setCreateAt(now);

            return userAddressRepository.save(userAddress);
        }catch(Exception e){
            throw(e);
        }
    }
    
        public Object updateUserAddress(UserAddressModel userAddress) {
        try{
            //Update updated_at date
            Timestamp now = getTimestampNow();
            userAddress.setUpdatedAt(now);

            return userAddressRepository.save(userAddress);
        }catch(Exception e){
            throw(e);
        }
    }
    
}
