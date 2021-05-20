package com.example.demo.services;

import com.example.demo.exception.ApiAuthException;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.models.UpdateUserRequestModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel getUserById(String id){

        return userRepository.findById(id)
                .orElse(null);
    }
    //TODO
    /*public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElse(null);
    }*/


    public UserModel createUser(UserModel user) {
        try{
            return userRepository.save(user);
        }catch(DataIntegrityViolationException e){
            throw new ApiRequestException("The email is already on use. Please try with another one.");
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }

    public UserModel updateUser(UserModel user) throws ApiAuthException {return userRepository.save(user); }


}
