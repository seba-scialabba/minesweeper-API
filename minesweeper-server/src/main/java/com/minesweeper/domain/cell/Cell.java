package com.minesweeper.domain.cell;

import static com.minesweeper.domain.cell.CellVisibleStatus.HIDDEN;
import static com.minesweeper.domain.cell.CellVisibleStatus.RED_FLAG;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_RED_FLAG;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

import com.minesweeper.domain.InteractionResult;
import com.minesweeper.exception.InvalidCommandException;

@Data
public abstract class Cell {
	protected List<Cell> neighbours;
	protected CellVisibleStatus visibleStatus = CellVisibleStatus.HIDDEN;

	/**
	 * Mark the visibility of the cell with a red flag
	 *
	 * @return whether the mark corresponds to a mine cell or not
	 */
	public InteractionResult markRedFlag() {
		if (!SUPPORTS_MARKING_RED_FLAG.contains(visibleStatus)) {
			String allowedStatuses = SUPPORTS_MARKING_RED_FLAG.stream().map(CellVisibleStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Marking with red flag only supported for cell statuses: %s", allowedStatuses));
		}
		visibleStatus = RED_FLAG;
		return InteractionResult.RED_FLAG_ADDED_INCORRECTLY;
	}

	/**
	 * Remove the  the visibility of the cell with a red flag
	 */
	public void removeRedFlag() {
		if (visibleStatus != RED_FLAG) {
			throw new InvalidCommandException("Removing red flag only supported for cell status RED_FLAG");
		}
		visibleStatus = HIDDEN;
	}
}
