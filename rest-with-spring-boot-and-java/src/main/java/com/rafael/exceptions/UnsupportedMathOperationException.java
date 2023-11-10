package com.rafael.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //anotação usada para indicar ao Spring o código de status HTTP
public class UnsupportedMathOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public UnsupportedMathOperationException(String ex) {
		super(ex);
	}
	

}
