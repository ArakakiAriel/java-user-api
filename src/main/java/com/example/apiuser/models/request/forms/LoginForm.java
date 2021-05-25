package com.example.apiuser.models.request.forms;

public class LoginForm {
    String email;
    String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
