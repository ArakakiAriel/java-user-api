package com.example.demo.exception;

import com.example.demo.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.example.demo.utils.DateUtils.parseDate;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){

        //Create a payload containing the exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                badRequest,
                parseDate(new Timestamp(System.currentTimeMillis()))
        );

        //Return the response entity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleApiDataNotFoundException(DataNotFoundException e){

        //Create a payload containing the exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                notFound,
                parseDate(new Timestamp(System.currentTimeMillis()))
        );

        //Return the response entity
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {ApiAuthException.class})
    public ResponseEntity<Object> handleApiAuthException(ApiAuthException e){

        //Create a payload containing the exception details
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                unauthorized,
                parseDate(new Timestamp(System.currentTimeMillis()))
        );

        //Return the response entity
        return new ResponseEntity<>(apiException, unauthorized);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleInternalErrorException(Exception e){
        System.out.println("ALSKDJLJA: " + e.hashCode() + e);
        HttpStatus notHandled = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                notHandled,
                parseDate(new Timestamp(System.currentTimeMillis()))

        );

        return new ResponseEntity<>(apiException, notHandled);
    }

}
