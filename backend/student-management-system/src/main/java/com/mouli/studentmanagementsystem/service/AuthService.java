package com.mouli.studentmanagementsystem.service;


import java.util.Random;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mouli.studentmanagementsystem.dto.ResetPasswordRequestDTO;
import com.mouli.studentmanagementsystem.dto.AuthResponseDTO;
import com.mouli.studentmanagementsystem.dto.ForgotPasswordRequestDTO;
import com.mouli.studentmanagementsystem.dto.LoginRequestDTO;
import com.mouli.studentmanagementsystem.dto.RegisterRequestDTO;
import com.mouli.studentmanagementsystem.entity.Role;
import com.mouli.studentmanagementsystem.entity.User;
import com.mouli.studentmanagementsystem.exception.DuplicateUserEmailException;
import com.mouli.studentmanagementsystem.exception.DuplicateUsernameException;
import com.mouli.studentmanagementsystem.exception.InvalidCredentialsException;
import com.mouli.studentmanagementsystem.repository.UserRepository;
import com.mouli.studentmanagementsystem.security.JwtService;
import com.mouli.studentmanagementsystem.dto.VerifyOtpRequestDTO;
import com.mouli.studentmanagementsystem.service.EmailService;
import com.mouli.studentmanagementsystem.dto.SendRegistrationOtpRequestDTO;
import com.mouli.studentmanagementsystem.dto.VerifyRegistrationOtpRequestDTO;
import com.mouli.studentmanagementsystem.entity.PendingRegistration;
import com.mouli.studentmanagementsystem.repository.PendingRegistrationRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PendingRegistrationRepository pendingRegistrationRepository;

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
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponseDTO(
                "Login successful",
                token,
                user.getRole().name());
    }

    // Forgot Password
    public AuthResponseDTO forgotPassword(
            ForgotPasswordRequestDTO requestDTO) {

        User user = userRepository
                .findByEmail(
                        requestDTO.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email not found"));

        String otp =
                String.valueOf(
                        100000 +
                        new Random().nextInt(900000));

        user.setOtp(otp);

        user.setOtpExpiry(
                System.currentTimeMillis()
                        + 5 * 60 * 1000);

        userRepository.save(user);

        emailService.sendOtpEmail(
                user.getEmail(),
                otp);

        return new AuthResponseDTO(
                "OTP generated successfully");
        
    }
 // Verify OTP
    public AuthResponseDTO verifyOtp(
            VerifyOtpRequestDTO requestDTO) {

        User user = userRepository
                .findByEmail(
                        requestDTO.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email not found"));

        if (user.getOtp() == null) {

            throw new RuntimeException(
                    "No OTP generated");
        }

        if (!user.getOtp().equals(
                requestDTO.getOtp())) {

            throw new RuntimeException(
                    "Invalid OTP");
        }

        if (System.currentTimeMillis()
                > user.getOtpExpiry()) {

            throw new RuntimeException(
                    "OTP expired");
        }

        return new AuthResponseDTO(
                "OTP verified successfully");
    }
    public AuthResponseDTO resetPassword(
            ResetPasswordRequestDTO requestDTO) {

        User user = userRepository
                .findByEmail(
                        requestDTO.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email not found"));

        user.setPassword(
                passwordEncoder.encode(
                        requestDTO.getNewPassword()));

        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);

        return new AuthResponseDTO(
                "Password reset successfully");
    }
    
    public AuthResponseDTO sendRegistrationOtp(
            SendRegistrationOtpRequestDTO requestDTO) {

        if (userRepository.existsByUsername(
                requestDTO.getUsername())) {

            throw new DuplicateUsernameException(
                    "Username already exists");
        }

        if (userRepository.existsByEmail(
                requestDTO.getEmail())) {

            throw new DuplicateUserEmailException(
                    "Email already exists");
        }

        String otp =
                String.valueOf(
                        100000 +
                        new Random().nextInt(900000));

        PendingRegistration pending =
                pendingRegistrationRepository
                        .findByEmail(
                                requestDTO.getEmail())
                        .orElse(
                                new PendingRegistration());

        pending.setUsername(
                requestDTO.getUsername());

        pending.setEmail(
                requestDTO.getEmail());

        pending.setPassword(
                passwordEncoder.encode(
                        requestDTO.getPassword()));

        pending.setOtp(
                otp);

        pending.setOtpExpiry(
                System.currentTimeMillis()
                        + 5 * 60 * 1000);

        pendingRegistrationRepository.save(
                pending);

        emailService.sendOtpEmail(
                requestDTO.getEmail(),
                otp);

        return new AuthResponseDTO(
                "Registration OTP sent successfully");
    }
    
    public AuthResponseDTO verifyRegistrationOtp(
            VerifyRegistrationOtpRequestDTO requestDTO) {

        PendingRegistration pending =
                pendingRegistrationRepository
                        .findByEmail(
                                requestDTO.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Registration request not found"));

        if (!pending.getOtp().equals(
                requestDTO.getOtp())) {

            throw new RuntimeException(
                    "Invalid OTP");
        }

        if (System.currentTimeMillis()
                > pending.getOtpExpiry()) {

            throw new RuntimeException(
                    "OTP expired");
        }

        User user = new User();

        user.setUsername(
                pending.getUsername());

        user.setEmail(
                pending.getEmail());

        user.setPassword(
                pending.getPassword());

        user.setRole(
                Role.ROLE_USER);

        userRepository.save(
                user);

        pendingRegistrationRepository.delete(
                pending);

        return new AuthResponseDTO(
                "Registration successful");
    }
}