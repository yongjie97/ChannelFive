package com.channelfive.easyuni.exceptions;

public class OldPasswordNotMatchException extends Exception {

    public OldPasswordNotMatchException() {
        super();
    }

    public OldPasswordNotMatchException(String message) {
        super(message);
    }

    public OldPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}