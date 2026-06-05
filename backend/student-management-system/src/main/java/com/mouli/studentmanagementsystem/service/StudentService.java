package com.mouli.studentmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouli.studentmanagementsystem.entity.Student;
import com.mouli.studentmanagementsystem.exception.ResourceNotFoundException;
import com.mouli.studentmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Save student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get student by ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));
    }

    // Update student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setAddress(updatedStudent.getAddress());

        return studentRepository.save(existingStudent);
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
