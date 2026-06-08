package com.mouli.studentmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.mouli.studentmanagementsystem.dto.StudentRequestDTO;
import com.mouli.studentmanagementsystem.dto.StudentResponseDTO;
import com.mouli.studentmanagementsystem.entity.Student;
import com.mouli.studentmanagementsystem.exception.DuplicateEmailException;
import com.mouli.studentmanagementsystem.exception.ResourceNotFoundException;
import com.mouli.studentmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Convert Entity -> ResponseDTO
    private StudentResponseDTO convertToResponseDTO(Student student) {

        StudentResponseDTO responseDTO =
                new StudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setFirstName(student.getFirstName());
        responseDTO.setLastName(student.getLastName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setPhone(student.getPhone());
        responseDTO.setAddress(student.getAddress());

        return responseDTO;
    }

    // Convert RequestDTO -> Entity
    private Student convertToEntity(
            StudentRequestDTO requestDTO) {

        Student student = new Student();

        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());
        student.setPhone(requestDTO.getPhone());
        student.setAddress(requestDTO.getAddress());

        return student;
    }

    // Get all students
    public List<StudentResponseDTO> getAllStudents() {

        List<Student> students =
                studentRepository.findAll();

        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get students with pagination
    public List<StudentResponseDTO> getStudentsWithPaginationAndSorting(
            int page,
            int size,
            String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy));

        Page<Student> studentPage =
                studentRepository.findAll(pageable);

        return studentPage.getContent()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Save student using DTO
    public StudentResponseDTO saveStudent(
            StudentRequestDTO requestDTO) {

        if (studentRepository.existsByEmail(
                requestDTO.getEmail())) {

            throw new DuplicateEmailException(
                    "Email already exists: "
                            + requestDTO.getEmail());
        }

        Student student =
                convertToEntity(requestDTO);

        Student savedStudent =
                studentRepository.save(student);

        return convertToResponseDTO(savedStudent);
    }

    // Get student by ID
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        return convertToResponseDTO(student);
    }
  // Search students by first name
    public List<StudentResponseDTO> searchStudents(
            String keyword) {

        List<Student> students =
                studentRepository
                        .findByFirstNameContainingIgnoreCase(
                                keyword);

        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Update student
    public StudentResponseDTO updateStudent(
            Long id,
            StudentRequestDTO requestDTO) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        existingStudent.setFirstName(requestDTO.getFirstName());
        existingStudent.setLastName(requestDTO.getLastName());
        existingStudent.setEmail(requestDTO.getEmail());
        existingStudent.setPhone(requestDTO.getPhone());
        existingStudent.setAddress(requestDTO.getAddress());

        Student savedStudent =
                studentRepository.save(existingStudent);

        return convertToResponseDTO(savedStudent);
    }

    // Delete student
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
    }
}
