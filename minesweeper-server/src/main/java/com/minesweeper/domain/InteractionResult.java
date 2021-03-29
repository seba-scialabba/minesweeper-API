package com.minesweeper.domain;

public enum InteractionResult {
	CELL_REVEALED,
	EXPLOSION,
	RED_FLAG_ADDED_INCORRECTLY,	// Added red flag on cell without mine
	RED_FLAG_ADDED_CORRECTLY,	// Added red flag on mine cell
}
