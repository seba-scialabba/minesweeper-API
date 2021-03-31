package com.minesweeper.domain.minefield;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Minefield<T extends MinefieldConfig> {
	protected T config;

	/**
	 * Creates a new minefield given the config
	 */
	public void initialize() {
		fillWithEmptyCells();
		positionRandomMines();
	}

	protected abstract void fillWithEmptyCells();

	protected abstract void positionRandomMines();
}
