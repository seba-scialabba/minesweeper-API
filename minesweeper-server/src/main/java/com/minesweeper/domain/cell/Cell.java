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
public abstract class Cell implements ICell {
	protected List<Cell> neighbours;
	protected CellVisibleStatus visibleStatus = CellVisibleStatus.HIDDEN;

	@Override
	public InteractionResult markRedFlag() {
		flagCell(SUPPORTS_MARKING_RED_FLAG, RED_FLAG);
		return InteractionResult.RED_FLAG_ADDED_INCORRECTLY;
	}

	@Override
	public void removeRedFlag() {
		flagCell(Sets.immutableEnumSet(RED_FLAG), HIDDEN);
	}

	@Override
	public void markQuestion() {
		flagCell(SUPPORTS_MARKING_QUESTION, QUESTION);
	}

	@Override
	public void removeQuestion() {
		flagCell(Sets.immutableEnumSet(QUESTION), HIDDEN);
	}

	@Override
	public void increaseMineProximityCount() {
		// Do nothing by default
	}

	/**
	 * By default we do not reveal a cell while exploring starting from an empty cell
	 *
	 * @return if the current cell must be revealed or not
	 */
	protected boolean mustRevealWhileExploring() {
		return false;
	}

	private void flagCell(Set<CellVisibleStatus> allowedStatusesSet, CellVisibleStatus newStatus) {
		if (!allowedStatusesSet.contains(visibleStatus)) {
			String allowedStatuses = allowedStatusesSet.stream().map(CellVisibleStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Interaction only supported for cell statuses: %s", allowedStatuses));
		}
		visibleStatus = newStatus;
	}
}
