package com.example.apiuser.utils.response;

import static com.example.apiuser.utils.DateUtils.getStringTimestampNow;

public class Response {
    Object response;
    String message;
     int status_code;
    String date;

    public Response(Object response, String message, int status_code) {
        this.response = response;
        this.message = message;
        this.status_code = status_code;
        this.date = getStringTimestampNow();
    }

    public Object getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getDate() {
        return date;
    }
}
