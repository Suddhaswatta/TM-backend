package com.task.Task.exception;

public class ExceptionResponse {
	
	private String error;
	
	

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [error=" + error + "]";
	}
	
	
	
	

}
