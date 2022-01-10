package com.apps.org.custom.exceptions.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apps.org.custom.exceptions.Errors;
import com.apps.org.custom.exceptions.handler.EmployeeNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ExceptionHandler(value = { EmployeeNotFoundException.class })
	public ResponseEntity<?> handleException(EmployeeNotFoundException ex) {
		logger.error("Exception: ", ex.getLocalizedMessage());
		Errors errors = new Errors(HttpStatus.NO_CONTENT.value(), ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<?> handleNullException(NullPointerException ex) {
		logger.error("Exception: ", ex.getLocalizedMessage());
		return ResponseEntity.badRequest().body(new Errors(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
		logger.error("Exception: ", ex.getLocalizedMessage());
		Errors errors = new Errors(HttpStatus.CONFLICT.value(), ex.getCause().getMessage());
		return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { javax.validation.ConstraintViolationException.class })
	public ResponseEntity<?> handleConstraintViolationException(javax.validation.ConstraintViolationException ex) {
		
		logger.error("Exception: ", ex.getLocalizedMessage());
		List<String> errorMessages = ex.getConstraintViolations()
	            .stream()
	            .map(javax.validation.ConstraintViolation::getMessage)
	            .collect(Collectors.toList());
	    return ResponseEntity.badRequest().body(new Errors(HttpStatus.BAD_REQUEST.value(), errorMessages.toString()));
	}

}