package com.example.apiuser.services;

import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.RoleModel;
import com.example.apiuser.models.ShowUserModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.repositories.RoleRepository;
import com.example.apiuser.repositories.UserRepository;
import com.example.apiuser.utils.Security;
import com.example.apiuser.utils.response.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.apiuser.utils.PropertyValues;

import java.io.IOException;
import java.util.*;

import java.sql.Timestamp;

import static com.example.apiuser.utils.DateUtils.getTimestampNow;
import static com.example.apiuser.utils.JwtToken.createJWT;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel getUserById(String id){

        UserModel user =  userRepository.findById(id)
                .orElse(null);


        if(user == null){
            throw new DataNotFoundException("User not found in the database");
        }

        return user;
    }
    

    public UserModel getUserByEmail(String email){
        
        return userRepository.findByEmail(email);
    }

    public Boolean validatePassword(String userId, String password) throws IOException {
        UserModel user = userRepository.findById(userId)
                .orElse(null);
        if(user == null){
            throw new DataNotFoundException("No user found with that ID");
        }

        if(!BCrypt.checkpw(password, user.getPassword())){
            throw new ApiAuthException("The old password is incorrect");
        }


        return true;
    }


    //Creates the user in the db
    public UserModel createUser(UserModel user) throws IOException {
        try{

            //Creating a random UUID to set the user's id
            String uuid = UUID.randomUUID().toString();
            user.setUserId(uuid);
            //Setting the "Created at" value
            Timestamp now = getTimestampNow();
            user.setCreatedAt(now);
            //Encrypting the password before saving
            String pwd = user.getPassword();
            String hashedPwd = Security.encryptPassword(pwd);
            user.setPassword(hashedPwd);

            //Setting the "status" value
            user.setStatus("ACTIVE");

            RoleModel role = roleRepository.findByRoleName("USER");
            if(role == null){
                throw new DataNotFoundException("The Role USER is not created yet");
            }
            user.getUserRoles().add(role);

            return userRepository.save(user);
        }catch(DataIntegrityViolationException e){
            throw new ApiRequestException("The email is already on use. Please try with another one.");
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }

    public ShowUserModel login(UserModel user, String password){
        String hashedPwd = user.getPassword();

        if(!BCrypt.checkpw(password, hashedPwd)){
            throw new ApiAuthException("User or password Incorrect");
        }

        //Update last login date
        Timestamp now = getTimestampNow();
        user.setLastLogin(now);
        userRepository.save(user);

        ShowUserModel jwtUser = new ShowUserModel(user);

        setRolesShowUserModel(user, jwtUser);

        return createJWT(jwtUser) ;
    }

    public UserModel updateUser(UserModel user) throws ApiAuthException {


        return userRepository.save(user);
    }

    public ShowUserModel changePassword(UserModel user, String newPassword) throws IOException {

        String hashedPwd = Security.encryptPassword(newPassword);
        user.setPassword(hashedPwd);

        //Update updated_at date
        Timestamp now = getTimestampNow();
        user.setUpdatedAt(now);

        userRepository.save(user);

        ShowUserModel userModel = new ShowUserModel(user);
        setRolesShowUserModel(user, userModel);

        return userModel;
    }

    public void setRolesShowUserModel(UserModel user, ShowUserModel showUserModel){
        Set<RoleModel> userRoles = user.getUserRoles();
        Set<String> roleNames = new HashSet<>();
        for (RoleModel rol:userRoles
        ) {
            roleNames.add(rol.getRoleName());
        }
        showUserModel.setRoles(roleNames);
    }

}
