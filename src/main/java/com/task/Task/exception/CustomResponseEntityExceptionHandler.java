package com.task.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectNotFoundException(TaskNotFoundException ex, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

	}

}
