package com.example.apiuser.models.request.forms;

import javax.validation.constraints.NotNull;

public class CreateUserForm {
    @NotNull(message="(Name was not provided)")
    String fullName;
    @NotNull(message="(Email was not provided)")
    String email;
    @NotNull(message="(Password was not provided)")
    String password;

    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

   

}
