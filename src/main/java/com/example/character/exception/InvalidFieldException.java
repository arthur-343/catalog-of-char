package com.example.character.exception;

public class InvalidFieldException extends RuntimeException {
  	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String message) {
        super(message);
    }
}
