package com.channelfive.easyuni.exceptions;

public class UniversityNotFoundException extends Exception {

    public UniversityNotFoundException() {
        super();
    }

    public UniversityNotFoundException(String message) {
        super(message);
    }

    public UniversityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}