package com.channelfive.easyuni.exceptions;

public class UserNotVerifiedException extends Exception {

    public UserNotVerifiedException() {
        super();
    }


    public UserNotVerifiedException(String message) {
        super(message);
    }


    public UserNotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}