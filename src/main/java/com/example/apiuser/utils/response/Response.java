package com.example.apiuser.utils.response;

import static com.example.apiuser.utils.DateUtils.getStringTimestampNow;

public class Response {
    Object response;
    String message;
     int statusCode;
    Throwable error;
    String timestamp;

    public Response(Throwable error, String message, int statusCode) {
        this.response = null;
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.timestamp = getStringTimestampNow();
    }

    public Response(Object response, String message, int statusCode) {
        this.response = response;
        this.message = message;
        this.statusCode = statusCode;
        this.error = null;
        this.timestamp = getStringTimestampNow();
    }

    public Object getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getError() {
        return error;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
