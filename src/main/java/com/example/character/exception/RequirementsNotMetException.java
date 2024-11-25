package com.example.character.exception;

public class RequirementsNotMetException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequirementsNotMetException(String message) {
        super(message);
    }
}
