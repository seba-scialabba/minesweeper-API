package com.minesweeper.domain.minefield;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Minefield {
	protected MinefieldConfig config;

	/**
	 * Creates a new minefield given the config
	 */
	public abstract void initialize();
}
