package com.minesweeper.domain.cell;

import static com.minesweeper.domain.cell.CellVisibilityStatus.SUPPORTS_MARKING_RED_FLAG;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

import com.minesweeper.domain.InteractionResult;
import com.minesweeper.exception.InvalidCommandException;

@Data
public abstract class Cell {
	protected List<Cell> neighbours;
	protected CellVisibilityStatus visibilityStatus = CellVisibilityStatus.HIDDEN;

	/**
	 * Mark the visibility of the cell with a red flag
	 */
	public InteractionResult markRedFlag() {
		if (!SUPPORTS_MARKING_RED_FLAG.contains(visibilityStatus)) {
			String allowedStatuses = SUPPORTS_MARKING_RED_FLAG.stream().map(CellVisibilityStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Marking with red flag only supported for cell statuses: %s", allowedStatuses));
		}
		return InteractionResult.RED_FLAG_ADDED_INCORRECTLY;
	}
}
