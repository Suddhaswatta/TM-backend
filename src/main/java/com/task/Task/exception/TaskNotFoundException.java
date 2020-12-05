package com.task.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4926669612192011671L;

	public TaskNotFoundException(String message) {
		super(message);
	}

		

}
