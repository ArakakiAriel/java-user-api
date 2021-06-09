package com.example.apiuser.controller;
//It will take the calls from outside


import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.ShowUserModel;
import com.example.apiuser.models.UpdateUserRequestModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.models.request.forms.CreateUserForm;
import com.example.apiuser.models.request.forms.LoginForm;
import com.example.apiuser.models.request.forms.PasswordChangeForm;
import com.example.apiuser.services.UserService;
import com.example.apiuser.utils.response.CommonResponse;
import com.example.apiuser.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;



    //TODO: We have to delete it for production
    @GetMapping("/get/all")
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    //Get User By ID
    @GetMapping("/get/{userId}")
    public Response getUserById(@PathVariable("userId") String userId){
        UserModel user = userService.getUserById(userId);
        if(user == null){
            throw new DataNotFoundException("User not found in the database");
        }

        ShowUserModel showUser = new ShowUserModel(user);

        return CommonResponse.setResponseWithOk(showUser, "", 200);
    }

    @PostMapping("/add")
    public Response createUser(@Valid @RequestBody CreateUserForm userParameters, Errors errors) throws IOException {
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }

        //Creating a UserModel and filling it with the request values
        UserModel user  = new UserModel();
        user.setPassword(userParameters.getPassword());
        user.setEmail(userParameters.getEmail());
        user.setFullName(userParameters.getFullName());


        return CommonResponse.setResponseWithOk(this.userService.createUser(user), "User successfully created", 201);
    }


    //TODO: Falta hacer
    @PutMapping("update/{userId}")
    public UserModel updateUser(@PathVariable("userId") String userId,@Valid @RequestBody UpdateUserRequestModel updatedUser){

        UserModel user = this.userService.getUserById(userId);




        return null;
        //return this.userService.updateUser(user);
    }


    @PutMapping("/password")
    public Response changePassword(@Valid @RequestBody PasswordChangeForm request, Errors errors) throws IOException {
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }
        String userId = request.getUserId();
        UserModel user = this.userService.getUserById(userId);

        String newPassword = request.getNewPassword();

        return CommonResponse.setResponseWithOk(this.userService.changePassword(user, newPassword),"Password changed successfully", 201);
    }


    @PostMapping("/login")
    public Response login(@Valid @RequestBody LoginForm loginForm){

        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        UserModel user = this.userService.getUserByEmail(email);

        if(user == null){
            throw new ApiAuthException("User or password incorrect");
        }

        //String jwtToken = this.userService.login(user, password);

        return CommonResponse.setResponseWithOk(this.userService.login(user, password), "Login succeed", 200);
    }

}
