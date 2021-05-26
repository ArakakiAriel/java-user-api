package com.example.apiuser.models.request.forms;

public class PasswordChangeForm {
    String password;

    public PasswordChangeForm(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
