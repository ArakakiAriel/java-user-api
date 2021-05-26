package com.example.apiuser.exception;

//TODO: A ELIMINAR SE USA DIRECTAMENTE EL RESPONSE
public class ApiException {
    private final String message;
    private final Throwable error;
    private final int code;
    private final String timestamp;

    public ApiException(String message, Throwable error, int code, String timestamp) {
        this.message = message;
        this.error = error;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
