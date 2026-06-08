package com.mouli.studentmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouli.studentmanagementsystem.dto.AuthResponseDTO;
import com.mouli.studentmanagementsystem.dto.LoginRequestDTO;
import com.mouli.studentmanagementsystem.dto.RegisterRequestDTO;
import com.mouli.studentmanagementsystem.entity.Role;
import com.mouli.studentmanagementsystem.entity.User;
import com.mouli.studentmanagementsystem.exception.DuplicateUserEmailException;
import com.mouli.studentmanagementsystem.exception.DuplicateUsernameException;
import com.mouli.studentmanagementsystem.exception.InvalidCredentialsException;
import com.mouli.studentmanagementsystem.repository.UserRepository;
import com.mouli.studentmanagementsystem.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Register User
    public AuthResponseDTO registerUser(
            RegisterRequestDTO requestDTO) {

        if (userRepository.existsByUsername(
                requestDTO.getUsername())) {

            throw new DuplicateUsernameException(
                    "Username already exists: "
                            + requestDTO.getUsername());
        }

        if (userRepository.existsByEmail(
                requestDTO.getEmail())) {

            throw new DuplicateUserEmailException(
                    "Email already exists: "
                            + requestDTO.getEmail());
        }

        User user = new User();

        user.setUsername(
                requestDTO.getUsername());

        user.setEmail(
                requestDTO.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        requestDTO.getPassword()));

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return new AuthResponseDTO(
                "User registered successfully");
    }

    // Login User
    public AuthResponseDTO loginUser(
            LoginRequestDTO requestDTO) {

        User user = userRepository
                .findByUsername(
                        requestDTO.getUsername())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid username or password"));

        boolean passwordMatches =
                passwordEncoder.matches(
                        requestDTO.getPassword(),
                        user.getPassword());

        if (!passwordMatches) {

            throw new InvalidCredentialsException(
                    "Invalid username or password");
        }

        String token =
                jwtService.generateToken(
                        user.getUsername());

        return new AuthResponseDTO(
                "Login successful",
                token);
    }
}