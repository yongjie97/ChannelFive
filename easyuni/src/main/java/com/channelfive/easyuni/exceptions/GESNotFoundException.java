package com.channelfive.easyuni.exceptions;

public class GESNotFoundException extends Exception {

    public GESNotFoundException() {
        super();
    }

    public GESNotFoundException(String message) {
        super(message);
    }

    public GESNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}