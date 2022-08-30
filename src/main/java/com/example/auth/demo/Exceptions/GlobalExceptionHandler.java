package com.example.auth.demo.Exceptions;

import com.example.auth.demo.Payloads.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
    String message = e.getMessage();
    ApiResponse response = new ApiResponse(message, false);
    return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
  }
}
