package com.mouli.studentmanagementsystem.exception;

public class DuplicateEnrollmentException
        extends RuntimeException {

    public DuplicateEnrollmentException(
            String message) {

        super(message);
    }
}