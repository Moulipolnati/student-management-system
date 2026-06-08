package com.mouli.studentmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouli.studentmanagementsystem.entity.Student;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    List<Student> findByFirstNameContainingIgnoreCase(
            String keyword);

}