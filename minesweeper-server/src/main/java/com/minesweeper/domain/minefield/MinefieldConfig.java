package com.minesweeper.domain.minefield;

import lombok.Data;

import com.minesweeper.exception.InvalidConfigurationException;

@Data
public class MinefieldConfig {
	private int minesCount;
	private int width;
	private int height;

	public void validate() {
		if (height < 2 || width < 2) {
			throw new InvalidConfigurationException("Minimum grid side is 2 x 2");
		}
		if (minesCount < 1) {
			throw new InvalidConfigurationException("There must be at least one mine");
		}
		if (height * width <= minesCount) {
			throw new InvalidConfigurationException("Mines count must be lower than the total space of the grid");
		}
	}
}
