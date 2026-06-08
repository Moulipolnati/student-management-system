package com.mouli.studentmanagementsystem.exception;

public class DuplicateUserEmailException
        extends RuntimeException {

    public DuplicateUserEmailException(
            String message) {

        super(message);
    }
}