package com.example.apiuser.utils.response;

public class CommonResponse {

    //TODO: fill the code
    public static Response setResponseWithOk(Object res, String message, int code){
        Response response = new Response(res, message, code);

        return response;
    }
}
