package com.example.apiuser.controller;

import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.models.RoleModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.services.RoleService;
import com.example.apiuser.utils.response.CommonResponse;
import com.example.apiuser.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/get/all")
    public ArrayList<RoleModel> getRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public  Response addRole(@Valid @RequestBody RoleModel role, Errors errors) {
        if(errors.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError e: allErrors){
                errorMessage += e.getDefaultMessage();
            }
            throw new ApiRequestException(errorMessage);
        }

        RoleModel response = roleService.createRole(role);

        return CommonResponse.setResponseWithOk(role, "Role created successfully", 201);

    }


}
