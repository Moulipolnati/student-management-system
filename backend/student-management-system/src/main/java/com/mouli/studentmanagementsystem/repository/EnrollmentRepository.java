package com.mouli.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouli.studentmanagementsystem.entity.Course;
import com.mouli.studentmanagementsystem.entity.Enrollment;
import com.mouli.studentmanagementsystem.entity.Student;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentAndCourse(
            Student student,
            Course course);

}