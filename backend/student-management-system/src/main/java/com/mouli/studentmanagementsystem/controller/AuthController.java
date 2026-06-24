package com.mouli.studentmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import com.mouli.studentmanagementsystem.dto.AuthResponseDTO;
import com.mouli.studentmanagementsystem.dto.ForgotPasswordRequestDTO;
import com.mouli.studentmanagementsystem.dto.LoginRequestDTO;
import com.mouli.studentmanagementsystem.dto.RegisterRequestDTO;
import com.mouli.studentmanagementsystem.service.AuthService;
import com.mouli.studentmanagementsystem.dto.VerifyOtpRequestDTO;
import com.mouli.studentmanagementsystem.dto.ResetPasswordRequestDTO;
import com.mouli.studentmanagementsystem.dto.SendRegistrationOtpRequestDTO;
import com.mouli.studentmanagementsystem.dto.VerifyRegistrationOtpRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO registerUser(
            @Valid @RequestBody RegisterRequestDTO requestDTO) {

        return authService.registerUser(requestDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO loginUser(
            @Valid @RequestBody LoginRequestDTO requestDTO) {

        return authService.loginUser(requestDTO);
    }
    
    @PostMapping("/forgot-password")
    public AuthResponseDTO forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO requestDTO) {

        System.out.println("FORGOT PASSWORD API HIT");

        return authService.forgotPassword(requestDTO);
    }
    
    @PostMapping("/reset-password")
    public AuthResponseDTO resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO requestDTO) {

        return authService.resetPassword(requestDTO);
    }
    
    @PostMapping("/verify-otp")
    public AuthResponseDTO verifyOtp(
            @Valid @RequestBody VerifyOtpRequestDTO requestDTO) {

        System.out.println("VERIFY OTP API HIT");

        return authService.verifyOtp(requestDTO);
    }
    
    @PostMapping("/send-registration-otp")
    public AuthResponseDTO sendRegistrationOtp(
            @Valid @RequestBody
            SendRegistrationOtpRequestDTO requestDTO) {

        return authService.sendRegistrationOtp(
                requestDTO);
    }

    @PostMapping("/verify-registration-otp")
    public AuthResponseDTO verifyRegistrationOtp(
            @Valid @RequestBody
            VerifyRegistrationOtpRequestDTO requestDTO) {

        return authService.verifyRegistrationOtp(
                requestDTO);
    }
    
}