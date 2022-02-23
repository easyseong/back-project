package com.example.backproject.advice.exception;

public class UserNotFoundCException extends RuntimeException {
    public UserNotFoundCException() {
        super();
    }

    public UserNotFoundCException(String message) {
        super(message);
    }

    public UserNotFoundCException(String message, Throwable cause) {
        super(message, cause);
    }
}
