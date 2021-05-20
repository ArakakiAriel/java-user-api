package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus code;
    private final String timestamp;

    public ApiException(String message, Throwable throwable, HttpStatus code, String timestamp) {
        this.message = message;
        this.throwable = throwable;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
