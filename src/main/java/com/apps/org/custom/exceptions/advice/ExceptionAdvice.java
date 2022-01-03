package com.apps.org.custom.exceptions.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apps.org.custom.exceptions.handler.EmployeeNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    
    @ExceptionHandler(value = { EmployeeNotFoundException.class })
    public ResponseEntity<?> handleException(EmployeeNotFoundException ex) {
    	logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = { NullPointerException.class })
    public ResponseEntity<?> handleNullException(NullPointerException ex) {
    	logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
   
}