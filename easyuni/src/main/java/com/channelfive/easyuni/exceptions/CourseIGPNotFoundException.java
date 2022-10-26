package com.channelfive.easyuni.exceptions;

public class CourseIGPNotFoundException extends Exception {

    public CourseIGPNotFoundException() {
        super();
    }

    public CourseIGPNotFoundException(String message) {
        super(message);
    }

    public CourseIGPNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}