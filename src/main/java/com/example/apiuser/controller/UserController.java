package com.example.apiuser.controller;
//It will take the calls from outside


import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.ShowUserModel;
import com.example.apiuser.models.UpdateUserRequestModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.models.request.forms.LoginForm;
import com.example.apiuser.services.UserService;
import com.example.apiuser.utils.response.CommonResponse;
import com.example.apiuser.utils.response.Response;
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

import static com.example.apiuser.utils.DateUtils.getTimestampNow;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/get")
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    //Get User By ID
    @GetMapping("/get/{userId}")
    public Response getUserById(@PathVariable("userId") String userId){
        UserModel user = userService.getUserById(userId);
        if(user == null){
            throw new DataNotFoundException("User Not Found");
        }

        ShowUserModel showUser = new ShowUserModel(user);

        return CommonResponse.setResponseWithOk(showUser, "", 200);
    }

    @PostMapping("/add")
    public Response createUser(@Valid @RequestBody UserModel user, Errors error){
        if(error.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = error.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }

        return CommonResponse.setResponseWithOk(this.userService.createUser(user), "User successfully created", 201);
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
    @PostMapping("login")
    public Response login(@RequestBody LoginForm loginForm){

        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        //TODO: find how to handle findBy* with CrudRepository
        UserModel user = this.userService.getUserByEmail(email);

        if(user == null){
            throw new ApiAuthException("User or password incorrect");
        }

        String response = this.userService.login(user, password);

        return CommonResponse.setResponseWithOk("SomeToken", response, 200);
    }

}
