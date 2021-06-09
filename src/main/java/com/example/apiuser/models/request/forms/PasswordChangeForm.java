package com.example.apiuser.models.request.forms;

import javax.validation.constraints.NotNull;

public class PasswordChangeForm {
    @NotNull(message="(UserId was not provided)")
    String userId;
    @NotNull(message="(Old Password was not provided)")
    String oldPassword;
    @NotNull(message="(New Password was not provided)")
    String newPassword;

    public PasswordChangeForm(String userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
