package com.dyulok.dewa.customexceptions;

public class LoginFailureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFailureException(String message, Throwable throwable) {
		super(message, throwable);
		// TODO Auto-generated constructor stub
	}

	public LoginFailureException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LoginFailureException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
