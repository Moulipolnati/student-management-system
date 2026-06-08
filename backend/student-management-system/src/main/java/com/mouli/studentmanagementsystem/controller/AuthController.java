package com.mouli.studentmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mouli.studentmanagementsystem.dto.AuthResponseDTO;
import com.mouli.studentmanagementsystem.dto.LoginRequestDTO;
import com.mouli.studentmanagementsystem.dto.RegisterRequestDTO;
import com.mouli.studentmanagementsystem.service.AuthService;

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
}