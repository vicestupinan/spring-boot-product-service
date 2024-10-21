package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.ErrorModel;
import com.example.demo.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionController {  
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorModel> errors = ex.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(error -> new ErrorModel(
                                            error.getDefaultMessage(),
                                            LocalDateTime.now()         
                                    ))
                                    .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ErrorModel(ex.getMessage(), LocalDateTime.now()));
    }
}
