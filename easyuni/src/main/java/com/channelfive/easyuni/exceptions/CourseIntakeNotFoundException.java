package com.channelfive.easyuni.exceptions;

public class CourseIntakeNotFoundException extends Exception {

    public CourseIntakeNotFoundException() {
        super();
    }

    public CourseIntakeNotFoundException(String message) {
        super(message);
    }

    public CourseIntakeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}