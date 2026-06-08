package com.mouli.studentmanagementsystem.exception;

public class DuplicateCourseException
        extends RuntimeException {

    public DuplicateCourseException(String message) {
        super(message);
    }
}