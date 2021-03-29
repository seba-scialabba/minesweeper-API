package com.minesweeper.domain.cell;

import static com.minesweeper.domain.cell.CellVisibleStatus.HIDDEN;
import static com.minesweeper.domain.cell.CellVisibleStatus.QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.RED_FLAG;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_RED_FLAG;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

import com.google.common.collect.Sets;
import com.minesweeper.domain.InteractionResult;
import com.minesweeper.exception.InvalidCommandException;

@Data
public abstract class Cell {
	protected List<Cell> neighbours;
	protected CellVisibleStatus visibleStatus = CellVisibleStatus.HIDDEN;

	/**
	 * Mark the cell with a red flag
	 * Only allowed if current visibility is HIDDEN or QUESTION
	 *
	 * @return whether the mark corresponds to a mine cell or not
	 */
	public InteractionResult markRedFlag() {
		flagCell(SUPPORTS_MARKING_RED_FLAG, RED_FLAG);
		return InteractionResult.RED_FLAG_ADDED_INCORRECTLY;
	}

	/**
	 * Remove the red flag mark
	 * Only allowed if current visibility is RED_FLAG
	 */
	public void removeRedFlag() {
		flagCell(Sets.immutableEnumSet(RED_FLAG), HIDDEN);
	}

	/**
	 * Mark the cell with a question mark
	 * Only allowed if current visibility is HIDDEN or RED_FLAG
	 */
	public void markQuestion() {
		flagCell(SUPPORTS_MARKING_QUESTION, QUESTION);
	}

	private void flagCell(Set<CellVisibleStatus> allowedStatusesSet, CellVisibleStatus newStatus) {
		if (!allowedStatusesSet.contains(visibleStatus)) {
			String allowedStatuses = allowedStatusesSet.stream().map(CellVisibleStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Interaction only supported for cell statuses: %s", allowedStatuses));
		}
		visibleStatus = newStatus;
	}
}
