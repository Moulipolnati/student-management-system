package com.mouli.studentmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mouli.studentmanagementsystem.dto.StudentResponseDTO;
import com.mouli.studentmanagementsystem.dto.StudentRequestDTO;
import com.mouli.studentmanagementsystem.entity.Student;
import com.mouli.studentmanagementsystem.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Add student
    @PostMapping("/students")
    public StudentResponseDTO addStudent(
            @Valid @RequestBody StudentRequestDTO requestDTO) {

        return studentService.saveStudent(requestDTO);
    }

    // Get student by ID
    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable Long id) {

        return studentService.getStudentById(id);
    }

    // Update student
    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO requestDTO) {

        return studentService.updateStudent(id, requestDTO);
    }

    // Delete student
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }
}