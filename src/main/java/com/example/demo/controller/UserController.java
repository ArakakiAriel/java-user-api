package com.example.demo.controller;
//It will take the calls from outside


import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.models.ShowUserModel;
import com.example.demo.models.UpdateUserRequestModel;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String hola(){
        return "Server is on";
    }


    @GetMapping("/get")
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    //Get User By ID
    @GetMapping("/get/{userId}")
    public ShowUserModel getUserById(@PathVariable("userId") String userId){
        UserModel user = userService.getUserById(userId);
        if(user == null){
            throw new DataNotFoundException("User Not Found");
        }

        ShowUserModel showUser = new ShowUserModel(user);
        return showUser;
    }

    @PostMapping("/add")
    public UserModel createUser(@Valid @RequestBody UserModel user, Errors error){
        if(error.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = error.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }
        //Creating a random UUID to set the user's id
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        //Setting the "Created at" value
        Timestamp now = new Timestamp(System.nanoTime());
        user.setCreatedAt(now);
        //Encrypting the password before saving //TODO: Cambiarlo por uno propio.
        String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(16));
        user.setPassword(hashedPwd);

        //Setting the "status" value
        user.setStatus("ACTIVE");


        return this.userService.createUser(user);
    }

    //TODO: Falta hacer
    @PutMapping("update/{userId}")
    public UserModel updateUser(@PathVariable("userId") String userId,@Valid @RequestBody UpdateUserRequestModel updatedUser){

        UserModel user = this.userService.getUserById(userId);
        //Setting the "Updated at" value
        Timestamp now = new Timestamp(System.nanoTime());
        //user.setUpdatedAt(now);

        return null;
        //return this.userService.updateUser(user);
    }

    //TODO A IMPLEMENTAR
    @PutMapping("login")
    public String login(@RequestBody String email, @RequestBody String password){

        return null;
    }

}
