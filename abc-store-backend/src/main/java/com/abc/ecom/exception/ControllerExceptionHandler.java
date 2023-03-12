package com.abc.ecom.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ExceptionResponse message = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
				ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ExceptionResponse> globalExceptionHandler(Exception ex, WebRequest request) {
		ExceptionResponse message = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
				ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
