package com.minesweeper.exception;

/**
 * Exception to be thrown when the user provides an invalid configuration to start a game
 */
public class InvalidConfigurationException extends RuntimeException {

	public InvalidConfigurationException(String message) {
		super(message);
	}
}
