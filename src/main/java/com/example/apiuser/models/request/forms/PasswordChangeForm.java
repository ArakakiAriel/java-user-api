package com.example.apiuser.models.request.forms;

import javax.validation.constraints.NotNull;

public class PasswordChangeForm {
    @NotNull(message="(UserId was not provided)")
    String userId;
    @NotNull(message="(Password was not provided)")
    String newPassword;

    public PasswordChangeForm(String userId, String newPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
