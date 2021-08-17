package com.example.apiuser.controller;
//It will take the calls from outside


import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.FindByEmailModel;
import com.example.apiuser.models.ShowUserModel;
import com.example.apiuser.models.UpdateUserRequestModel;
import com.example.apiuser.models.UserAddressModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.models.request.forms.CreateUserForm;
import com.example.apiuser.models.request.forms.LoginForm;
import com.example.apiuser.models.request.forms.PasswordChangeForm;
import com.example.apiuser.models.request.forms.UserAddressForm;
import com.example.apiuser.services.UserAddressService;
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
    @Autowired
    UserAddressService userAddressService;



    //TODO: We have to delete it for production
    @GetMapping("/get/all")
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    //Get User By ID
    @GetMapping("/get/{userId}")
    public Response getUserById(@PathVariable("userId") String userId){
        UserModel user = userService.getUserById(userId);


        ShowUserModel showUser = new ShowUserModel(user);
        userService.setRolesShowUserModel(user,showUser);
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

        String oldPassword = request.getOldPassword();
        if(!this.userService.validatePassword(userId,oldPassword)){
            throw new ApiAuthException("The old password is incorrect");
        }


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
    
    @PostMapping("/{userId}/address/add")
    public Response createUserAddress(@PathVariable("userId") String userId, @Valid @RequestBody UserAddressForm userAddressParameters, Errors errors) throws IOException {
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }
        
        UserModel user = userService.getUserById(userId);

        
        
        
        //Creating a UserModel and filling it with the request values
        UserAddressModel userAddress  = new UserAddressModel();


        if(user.getUserAddress().size() == 0){
            userAddress.setDefaultAddress(true);
        }else{
            userAddress.setDefaultAddress(false);
        }
       // userAddress.setUserId(userId);
        userAddress.setUser(user);
        userAddress.setPostalCode(userAddressParameters.getPostalCode());
        userAddress.setAddress1(userAddressParameters.getAddress1());
        userAddress.setAddress2(userAddressParameters.getAddress2());
        userAddress.setCity(userAddressParameters.getCity());
        userAddress.setCountry(userAddressParameters.getCountry());
        userAddress.setState(userAddressParameters.getState());
        userAddress.setPhoneNumber(userAddressParameters.getPhoneNumber());
        

        return CommonResponse.setResponseWithOk(this.userAddressService.createUserAddress(userAddress), "User Address successfully created", 201);
    }
    
      //Get UserAddress By ID
    @GetMapping("/{userId}/address/get")
    public Response getUserAddressById(@PathVariable("userId") String userId){
        UserModel user = userService.getUserById(userId);

        
        //ShowUserAddressModel showUserAddress = new ShowUserAddressModel();
        //userAddressService.setRolesShowUserModel(user,showUserAddress);
        return CommonResponse.setResponseWithOk(user.getUserAddress(), "", 200);
    }
    
    @GetMapping("/get")
    public Response getUserAddressByEmail(@RequestBody FindByEmailModel email){
        UserModel user = userService.getUserByEmail(email.getEmail());
        
        return CommonResponse.setResponseWithOk(user,"", 200);
    }
    
    @PutMapping("/{userId}/address/{userAddressId}/modify")
    public Response updateUserAddress(@PathVariable("userId") String userId,@PathVariable("userAddressId") String userAddressId, @Valid @RequestBody UserAddressForm userAddressParameters, Errors errors) throws IOException {
        
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }
        
        UserModel user = userService.getUserById(userId);
        //UserAddressModel userAddresses = userAddressService.getUserAddressById(userAddressId);
        
        //Creating a UserModel and filling it with the request values
        UserAddressModel userAddress  = userAddressService.getUserAddressById(userAddressId);
        
        if(userAddress.getUser()!= user){
            throw new ApiAuthException("Authorization Error");
        }
        
       // userAddress.setUserId(userId);
        userAddress.setUserAddressId(userAddressId);
        userAddress.setUser(user);
        userAddress.setPostalCode(userAddressParameters.getPostalCode());
        userAddress.setAddress1(userAddressParameters.getAddress1());
        userAddress.setAddress2(userAddressParameters.getAddress2());
        userAddress.setCity(userAddressParameters.getCity());
        userAddress.setCountry(userAddressParameters.getCountry());
        userAddress.setState(userAddressParameters.getState());
        userAddress.setPhoneNumber(userAddressParameters.getPhoneNumber());
        

        return CommonResponse.setResponseWithOk(this.userAddressService.updateUserAddress(userAddress), "User Address successfully updated", 201);
    }
    
    @PutMapping("/{userId}/address/{userAddressId}/default")
    public Response updateUserDefaultAddress(@PathVariable("userId") String userId,@PathVariable("userAddressId") String userAddressId ) throws IOException {
        
        UserModel user = userService.getUserById(userId);
        
        //Creating a UserModel and filling it with the request values
        UserAddressModel userAddress  = userAddressService.getUserAddressById(userAddressId);
        
        if(userAddress.getUser()!= user){
            throw new ApiAuthException("Authorization Error");
        }
             
        for (UserAddressModel address : user.getUserAddress()) {
        //write code
            if(address.getDefaultAddress()== true){
                address.setDefaultAddress(false);
                this.userAddressService.updateUserAddress(address);
                break;
            }
        }
        userAddress.setDefaultAddress(true);
        return CommonResponse.setResponseWithOk(this.userAddressService.updateUserAddress(userAddress), "User Default Address successfully updated", 201);
    }
    
 

}
