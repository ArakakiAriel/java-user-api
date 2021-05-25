package com.example.apiuser.exception;

public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final int code;
    private final String timestamp;

    public ApiException(String message, Throwable throwable, int code, String timestamp) {
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

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
