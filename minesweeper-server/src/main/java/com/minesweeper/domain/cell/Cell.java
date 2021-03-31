package com.minesweeper.domain.cell;

import static com.minesweeper.domain.cell.CellVisibleStatus.HIDDEN;
import static com.minesweeper.domain.cell.CellVisibleStatus.QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.RED_FLAG;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_RED_FLAG;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.common.collect.Sets;
import com.minesweeper.domain.InteractionResult;
import com.minesweeper.exception.InvalidCommandException;

@Data
@NoArgsConstructor
public abstract class Cell {
	protected Cell[] neighbours = new Cell[8];
	protected CellVisibleStatus visibleStatus = CellVisibleStatus.HIDDEN;

	public Cell(Cell cell) {
		neighbours = cell.neighbours;
		visibleStatus = cell.visibleStatus;
	}

	public abstract InteractionResult explore();

	public InteractionResult markRedFlag() {
		flagCell(SUPPORTS_MARKING_RED_FLAG, RED_FLAG);
		return InteractionResult.RED_FLAG_ADDED_INCORRECTLY;
	}

	public void removeRedFlag() {
		flagCell(Sets.immutableEnumSet(RED_FLAG), HIDDEN);
	}

	public void markQuestion() {
		flagCell(SUPPORTS_MARKING_QUESTION, QUESTION);
	}

	public void removeQuestion() {
		flagCell(Sets.immutableEnumSet(QUESTION), HIDDEN);
	}

	public Cell replaceWith(Cell newCell) {
		newCell.neighbours = neighbours;
		IntStream.range(0, neighbours.length).parallel()
			.filter(index -> neighbours[index] != null)
			.forEach(index -> neighbours[index] = newCell.increaseAdjacentMineCount(neighbours[index]));
		return newCell;
	}

	public void setNeighbour(int index, Cell neighbour) {
		neighbours[index] = neighbour;
	}

	public abstract boolean containsMine();

	protected abstract boolean mustBeRevealedWhileExploring();

	protected abstract Cell increaseAdjacentMineCount(Cell neighbourCell);

	protected abstract Cell autoIncreaseMineCount();

	private void flagCell(Set<CellVisibleStatus> allowedStatusesSet, CellVisibleStatus newStatus) {
		if (!allowedStatusesSet.contains(visibleStatus)) {
			String allowedStatuses = allowedStatusesSet.stream().map(CellVisibleStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Interaction only supported for cell statuses: %s", allowedStatuses));
		}
		visibleStatus = newStatus;
	}
}
