package com.mouli.studentmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mouli.studentmanagementsystem.dto.CourseRequestDTO;
import com.mouli.studentmanagementsystem.dto.CourseResponseDTO;
import com.mouli.studentmanagementsystem.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Add course
    @PostMapping
    public CourseResponseDTO addCourse(
            @Valid @RequestBody CourseRequestDTO requestDTO) {

        return courseService.saveCourse(requestDTO);
    }

    // Get course by ID
    @GetMapping("/{id}")
    public CourseResponseDTO getCourseById(
            @PathVariable Long id) {

        return courseService.getCourseById(id);
    }

    // Update course
    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO requestDTO) {

        return courseService.updateCourse(id, requestDTO);
    }

    // Delete course
    @DeleteMapping("/{id}")
    public String deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return "Course deleted successfully";
    }
}
