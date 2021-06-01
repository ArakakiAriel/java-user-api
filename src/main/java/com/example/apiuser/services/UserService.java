package com.example.apiuser.services;

import com.example.apiuser.exception.ApiAuthException;
import com.example.apiuser.exception.ApiRequestException;
import com.example.apiuser.exception.DataNotFoundException;
import com.example.apiuser.models.RoleModel;
import com.example.apiuser.models.ShowUserModel;
import com.example.apiuser.models.UserModel;
import com.example.apiuser.repositories.RoleRepository;
import com.example.apiuser.repositories.UserRepository;
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

    Properties config = PropertyValues.getPropValues("config.properties");
    int salt = Integer.parseInt(config.getProperty("password_salt"));

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserService() throws IOException {
    }

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel getUserById(String id){

        return userRepository.findById(id)
                .orElse(null);
    }

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
            String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(salt));
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

        ShowUserModel jwtUser = new ShowUserModel(user);

        Set<RoleModel> userRoles = user.getUserRoles();
        Set<String> roleNames = new HashSet<>();
        for (RoleModel rol:userRoles
             ) {
            roleNames.add(rol.getRoleName());
        }
        jwtUser.setRoles(roleNames);

        return createJWT(jwtUser) ;
    }

    public UserModel updateUser(UserModel user) throws ApiAuthException {


        return userRepository.save(user);
    }

    public String changePassword(UserModel user, String newPassword){

        Timestamp now = getTimestampNow();
        user.setUpdatedAt(now);

        //Encrypting the new password before saving //TODO: Cambiarlo por uno propio.
        String hashedPwd = BCrypt.hashpw(newPassword, BCrypt.gensalt(salt));
        user.setPassword(hashedPwd);

        userRepository.save(user);

        return "User password successfully changed";
    }

}
