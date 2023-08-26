package com.learning.hello.model.odometer;

public class ReadingSizeMismatchException extends ReadingException{
	
	private static final long serialVersionUID = 8360285256300216214L;

	public ReadingSizeMismatchException(String message) {
		super(message);
	}

}