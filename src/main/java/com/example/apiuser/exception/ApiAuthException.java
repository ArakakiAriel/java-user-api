package com.example.apiuser.exception;


public class ApiAuthException extends RuntimeException{
    public ApiAuthException(String message) {
        super(message);
    }

    public ApiAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
