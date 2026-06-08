package com.mouli.studentmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouli.studentmanagementsystem.dto.EnrollmentRequestDTO;
import com.mouli.studentmanagementsystem.dto.EnrollmentResponseDTO;
import com.mouli.studentmanagementsystem.entity.Course;
import com.mouli.studentmanagementsystem.entity.Enrollment;
import com.mouli.studentmanagementsystem.entity.Student;
import com.mouli.studentmanagementsystem.exception.ResourceNotFoundException;
import com.mouli.studentmanagementsystem.repository.CourseRepository;
import com.mouli.studentmanagementsystem.repository.EnrollmentRepository;
import com.mouli.studentmanagementsystem.repository.StudentRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Convert Entity -> ResponseDTO
    private EnrollmentResponseDTO convertToResponseDTO(
            Enrollment enrollment) {

        EnrollmentResponseDTO responseDTO =
                new EnrollmentResponseDTO();

        responseDTO.setId(enrollment.getId());

        responseDTO.setStudentId(
                enrollment.getStudent().getId());

        responseDTO.setStudentName(
                enrollment.getStudent().getFirstName()
                        + " "
                        + enrollment.getStudent().getLastName());

        responseDTO.setCourseId(
                enrollment.getCourse().getId());

        responseDTO.setCourseName(
                enrollment.getCourse().getCourseName());

        responseDTO.setEnrollmentDate(
                enrollment.getEnrollmentDate());

        return responseDTO;
    }

    // Get all enrollments
    public List<EnrollmentResponseDTO> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Create enrollment
    public EnrollmentResponseDTO createEnrollment(
            EnrollmentRequestDTO requestDTO) {

        Student student =
                studentRepository.findById(
                                requestDTO.getStudentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found with id: "
                                                + requestDTO.getStudentId()));

        Course course =
                courseRepository.findById(
                                requestDTO.getCourseId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course not found with id: "
                                                + requestDTO.getCourseId()));

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return convertToResponseDTO(savedEnrollment);
    }

    // Get enrollment by ID
    public EnrollmentResponseDTO getEnrollmentById(
            Long id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found with id: "
                                                + id));

        return convertToResponseDTO(enrollment);
    }

    // Delete enrollment
    public void deleteEnrollment(Long id) {

        if (!enrollmentRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Enrollment not found with id: "
                            + id);
        }

        enrollmentRepository.deleteById(id);
    }
}