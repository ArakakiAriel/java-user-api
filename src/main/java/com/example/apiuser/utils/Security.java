package com.example.apiuser.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class Security {
    //TODO: Cambiarlo por uno propio.
    public static String encryptPassword(String password) throws IOException {

        Properties config = PropertyValues.getPropValues("config.properties");
        int salt = Integer.parseInt(config.getProperty("password_salt"));


        String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(salt));

        return hashedPwd;
    }
}
