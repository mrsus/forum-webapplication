package com.dyulok.dewa.controller.validation;

public class ValidationErrorMessage {
	
	private int errorCode;
	private String message;
	
	
	public ValidationErrorMessage(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}


	public int getErrorCode() {
		return errorCode;
	}


	public String getMessage() {
		return message;
	}
	

}

