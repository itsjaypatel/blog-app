package com.example.demo.advice;

import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.util.ApiError;
import com.example.demo.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApiError.from(HttpStatus.NOT_FOUND.value(), ex.getMessage())));
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleEntityExistsException(ResourceConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(ApiError.from(HttpStatus.CONFLICT.value(), ex.getMessage())));
    }


}
