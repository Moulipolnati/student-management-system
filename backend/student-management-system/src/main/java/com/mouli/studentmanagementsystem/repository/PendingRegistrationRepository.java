package com.mouli.studentmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouli.studentmanagementsystem.entity.PendingRegistration;

@Repository
public interface PendingRegistrationRepository
        extends JpaRepository<PendingRegistration, Long> {

    Optional<PendingRegistration> findByEmail(
            String email);

    boolean existsByEmail(
            String email);

    boolean existsByUsername(
            String username);

    void deleteByEmail(
            String email);
}