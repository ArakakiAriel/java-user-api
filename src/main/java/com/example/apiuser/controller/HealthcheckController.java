package com.example.apiuser.controller;

import com.example.apiuser.utils.response.CommonResponse;
import com.example.apiuser.utils.response.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthcheckController {

    @GetMapping()
    public Response healthCheck(){

        return CommonResponse.setResponseWithOk(null, "Server is on",200);
    }

}
