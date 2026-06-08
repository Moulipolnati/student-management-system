package com.mouli.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouli.studentmanagementsystem.entity.Enrollment;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

}