package com.example.character.exception;

public class NotFindException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFindException(String message) {
        super(message);
    }
}
