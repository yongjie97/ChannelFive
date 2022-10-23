package com.channelfive.easyuni.exceptions;

public class DiscussionNotFoundException extends Exception {

    public DiscussionNotFoundException() {
        super();
    }

    public DiscussionNotFoundException(String message) {
        super(message);
    }

    public DiscussionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}