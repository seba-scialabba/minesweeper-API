package com.minesweeper.domain.cell;

import com.minesweeper.domain.InteractionResult;

public interface ICell {
	/**
	 * Marks the cell with a red flag
	 * Only allowed if current visibility is HIDDEN or QUESTION
	 *
	 * @return whether the mark corresponds to a mine cell or not
	 */
	InteractionResult markRedFlag();

	/**
	 * Removes the red flag mark
	 * Only allowed if current visibility is RED_FLAG
	 */
	void removeRedFlag();

	/**
	 * Marks the cell with a question mark
	 * Only allowed if current visibility is HIDDEN or RED_FLAG
	 */
	void markQuestion();

	/**
	 * Removes the question mark
	 * Only allowed if current visibility is QUESTION
	 */
	void removeQuestion();

	/**
	 * Explores the cell, what can lead to:
	 * - Reveal safe spaces if the cell is empty
	 * - Reveal the number of adjacent mines
	 * - Explosion
	 * Only allowed if current visibility is HIDDEN
	 *
	 * @return whether the mark corresponds to a mine cell or not
	 */
	InteractionResult explore();

	/**
	 * Increases the count of nearby mines
	 */
	void increaseMineProximityCount();
}
