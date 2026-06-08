package com.mouli.studentmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouli.studentmanagementsystem.dto.CourseRequestDTO;
import com.mouli.studentmanagementsystem.dto.CourseResponseDTO;
import com.mouli.studentmanagementsystem.entity.Course;
import com.mouli.studentmanagementsystem.exception.DuplicateCourseException;
import com.mouli.studentmanagementsystem.exception.ResourceNotFoundException;
import com.mouli.studentmanagementsystem.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Convert Entity -> ResponseDTO
    private CourseResponseDTO convertToResponseDTO(
            Course course) {

        CourseResponseDTO responseDTO =
                new CourseResponseDTO();

        responseDTO.setId(course.getId());
        responseDTO.setCourseName(course.getCourseName());
        responseDTO.setDescription(course.getDescription());
        responseDTO.setDuration(course.getDuration());
        responseDTO.setFee(course.getFee());

        return responseDTO;
    }

    // Convert RequestDTO -> Entity
    private Course convertToEntity(
            CourseRequestDTO requestDTO) {

        Course course = new Course();

        course.setCourseName(
                requestDTO.getCourseName());

        course.setDescription(
                requestDTO.getDescription());

        course.setDuration(
                requestDTO.getDuration());

        course.setFee(
                requestDTO.getFee());

        return course;
    }

    // Get all courses
    public List<CourseResponseDTO> getAllCourses() {

        List<Course> courses =
                courseRepository.findAll();

        return courses.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Save course
    public CourseResponseDTO saveCourse(
            CourseRequestDTO requestDTO) {

        if (courseRepository.existsByCourseName(
                requestDTO.getCourseName())) {

            throw new DuplicateCourseException(
                    "Course already exists: "
                            + requestDTO.getCourseName());
        }

        Course course =
                convertToEntity(requestDTO);

        Course savedCourse =
                courseRepository.save(course);

        return convertToResponseDTO(savedCourse);
    }

    // Get course by ID
    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: "
                                        + id));

        return convertToResponseDTO(course);
    }

    // Update course
    public CourseResponseDTO updateCourse(
            Long id,
            CourseRequestDTO requestDTO) {

        Course existingCourse =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course not found with id: "
                                                + id));

        existingCourse.setCourseName(
                requestDTO.getCourseName());

        existingCourse.setDescription(
                requestDTO.getDescription());

        existingCourse.setDuration(
                requestDTO.getDuration());

        existingCourse.setFee(
                requestDTO.getFee());

        Course savedCourse =
                courseRepository.save(existingCourse);

        return convertToResponseDTO(savedCourse);
    }

    // Delete course
    public void deleteCourse(Long id) {

        if (!courseRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Course not found with id: "
                            + id);
        }

        courseRepository.deleteById(id);
    }
}