package com.task.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 419646762979557642L;

	public UsernameNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
