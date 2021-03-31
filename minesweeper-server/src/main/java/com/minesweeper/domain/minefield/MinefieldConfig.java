package com.minesweeper.domain.minefield;

import lombok.Data;

@Data
public abstract class MinefieldConfig {
	private MinefieldType type;
	private int minesCount;
}
