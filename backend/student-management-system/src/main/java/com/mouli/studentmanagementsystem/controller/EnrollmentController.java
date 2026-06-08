package com.mouli.studentmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mouli.studentmanagementsystem.dto.EnrollmentRequestDTO;
import com.mouli.studentmanagementsystem.dto.EnrollmentResponseDTO;
import com.mouli.studentmanagementsystem.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Get all enrollments
    @GetMapping
    public List<EnrollmentResponseDTO> getAllEnrollments() {

        return enrollmentService.getAllEnrollments();
    }

    // Create enrollment
    @PostMapping
    public EnrollmentResponseDTO createEnrollment(
            @Valid @RequestBody EnrollmentRequestDTO requestDTO) {

        return enrollmentService.createEnrollment(requestDTO);
    }

    // Get enrollment by ID
    @GetMapping("/{id}")
    public EnrollmentResponseDTO getEnrollmentById(
            @PathVariable Long id) {

        return enrollmentService.getEnrollmentById(id);
    }

    // Delete enrollment
    @DeleteMapping("/{id}")
    public String deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return "Enrollment deleted successfully";
    }
}