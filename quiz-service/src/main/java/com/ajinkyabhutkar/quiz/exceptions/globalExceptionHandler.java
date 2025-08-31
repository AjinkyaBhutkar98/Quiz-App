package com.ajinkyabhutkar.quiz.exceptions;


import com.ajinkyabhutkar.quiz.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException exception){

        ApiResponse apiResponse=new ApiResponse(exception.getMessage(), false, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);

    }
}
