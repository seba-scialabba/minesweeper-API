package com.minesweeper.exception;

/**
 * Exception to be thrown when the user's command is not supported by the current game status
 */
public class InvalidCommandException extends RuntimeException {

	public InvalidCommandException(String message) {
		super(message);
	}
}
