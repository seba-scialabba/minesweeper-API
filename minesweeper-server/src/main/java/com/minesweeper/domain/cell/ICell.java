package com.minesweeper.domain.cell;

import com.minesweeper.domain.InteractionResult;

public interface ICell {
	/**
	 * Mark the cell with a red flag
	 * Only allowed if current visibility is HIDDEN or QUESTION
	 *
	 * @return whether the mark corresponds to a mine cell or not
	 */
	InteractionResult markRedFlag();

	/**
	 * Remove the red flag mark
	 * Only allowed if current visibility is RED_FLAG
	 */
	void removeRedFlag();

	/**
	 * Mark the cell with a question mark
	 * Only allowed if current visibility is HIDDEN or RED_FLAG
	 */
	void markQuestion();

	/**
	 * Remove the question mark
	 * Only allowed if current visibility is QUESTION
	 */
	void removeQuestion();
}
