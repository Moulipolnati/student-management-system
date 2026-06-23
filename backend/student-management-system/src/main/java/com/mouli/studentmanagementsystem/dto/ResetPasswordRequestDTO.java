package com.mouli.studentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequestDTO {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "New password is required")
    private String newPassword;

    public ResetPasswordRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email) {

        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(
            String newPassword) {

        this.newPassword = newPassword;
    }
}