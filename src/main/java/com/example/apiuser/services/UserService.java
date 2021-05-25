package com.example.apiuser.services;

import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.apiuser.utils.PropertyValues;
import java.util.Properties;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import static com.example.apiuser.utils.DateUtils.getTimestampNow;

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
    public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    //Creates the user in the db
    public UserModel createUser(UserModel user) {
        try{

            //Creating a random UUID to set the user's id
            String uuid = UUID.randomUUID().toString();
            user.setUserId(uuid);
            //Setting the "Created at" value
            Timestamp now = getTimestampNow();
            user.setCreatedAt(now);
            //Encrypting the password before saving //TODO: Cambiarlo por uno propio.
            String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(6));
            user.setPassword(hashedPwd);

            //Setting the "status" value
            user.setStatus("ACTIVE");

            return userRepository.save(user);
        }catch(DataIntegrityViolationException e){
            throw new ApiRequestException("The email is already on use. Please try with another one.");
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }

    public String login(UserModel user, String password){
        String hashedPwd = user.getPassword();

        if(!BCrypt.checkpw(password, hashedPwd)){
            throw new ApiAuthException("User or password Incorrect");
        }
        return "Welcome " + user.getFullName() ;
    }

    public UserModel updateUser(UserModel user) throws ApiAuthException {return userRepository.save(user); }


}
