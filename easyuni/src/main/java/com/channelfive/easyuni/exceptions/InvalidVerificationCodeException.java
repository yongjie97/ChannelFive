package com.channelfive.easyuni.exceptions;

public class InvalidVerificationCodeException extends Exception {

    public InvalidVerificationCodeException() {
        super();
    }


    public InvalidVerificationCodeException(String message) {
        super(message);
    }


    public InvalidVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}