package com.example.demo.exception;


public class ApiAuthException extends RuntimeException{
    public ApiAuthException(String message) {
        super(message);
    }

    public ApiAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
