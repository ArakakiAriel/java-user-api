package com.example.apiuser.controller;

import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.models.UserAddressModel;
import com.example.apiuser.models.request.forms.UserAddressForm;
import com.example.apiuser.services.UserAddressService;
import com.example.apiuser.utils.response.CommonResponse;
import com.example.apiuser.utils.response.Response;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user/address")

public class UserAddressController {
    
    @Autowired
    UserAddressService userAddressService;
    
    @PostMapping("/add")
    public Response createUserAddress(@Valid @RequestBody UserAddressForm userAddressParameters, Errors errors) throws IOException {
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }

        //Creating a UserModel and filling it with the request values
        UserAddressModel userAddress  = new UserAddressModel();
        
        //user.setPostalCode(u);
        //user.setAddress1();

        return CommonResponse.setResponseWithOk(this.userAddressService.createUserAddress(userAddress), "User Address successfully created", 201);
    }
    
       @DeleteMapping("/{userAddressId}/delete")
       public Response deleteUserAddress(@PathVariable("userAddressId") String userAddressId){

            UserAddressModel userAddresses = userAddressService.getUserAddressById(userAddressId);

           if(userAddresses.getDefaultAddress()){
               throw new ApiRequestException("Default address can't be deleted");
           }
 
        return CommonResponse.setResponseWithOk(this.userAddressService.deleteAddress(userAddressId), "Address succesfully deleted", 200);
    }
    
    
    
    
}