package com.example.apiuser.services;

import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.models.RoleModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleService() throws IOException {
    }

    public RoleModel createRole (RoleModel role){
        try{
            return roleRepository.save(role);

        }catch(DataIntegrityViolationException e){
            throw new ApiRequestException("The role name is already on use. Please try with another one.");
        }
    }

    public ArrayList<RoleModel> getAllRoles(){
        return (ArrayList<RoleModel>)roleRepository.findAll();
    }

    public RoleModel getRoleByName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
