package com.sf.exception;

public class MySqlTypeException extends RuntimeException {

	private static final long serialVersionUID = 4108576960739188171L;
	
	public MySqlTypeException() {
	}
	
	public MySqlTypeException(String message) {
		super(message);
	}
	
	public MySqlTypeException(Throwable t) {
		super(t);
	}
	
	public MySqlTypeException(String message, Throwable t) {
		super(message, t);
	}

}
