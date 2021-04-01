package com.minesweeper.domain.cell;

import static com.minesweeper.domain.cell.CellVisibleStatus.HIDDEN;
import static com.minesweeper.domain.cell.CellVisibleStatus.QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.RED_FLAG;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_QUESTION;
import static com.minesweeper.domain.cell.CellVisibleStatus.SUPPORTS_MARKING_RED_FLAG;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.common.collect.Sets;
import com.minesweeper.domain.InteractionResult;
import com.minesweeper.domain.minefield.Minefield;
import com.minesweeper.exception.InvalidCommandException;

@Data
@NoArgsConstructor
public abstract class Cell {
	protected Position position;
	protected CellVisibleStatus visibleStatus = CellVisibleStatus.HIDDEN;

	public Cell(Cell cell) {
		position = cell.position;
		visibleStatus = cell.visibleStatus;
	}

	public abstract InteractionResult explore(Minefield minefield);

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

	public abstract boolean containsMine();

	public abstract Cell affectNeighbourCell(Cell neighbourCell);

	protected abstract Cell incrementMineCount();

	protected abstract boolean mustBeRevealedWhileExploring();

	//protected abstract Cell increaseAdjacentMineCount(Cell neighbourCell);

	private void flagCell(Set<CellVisibleStatus> allowedStatusesSet, CellVisibleStatus newStatus) {
		if (!allowedStatusesSet.contains(visibleStatus)) {
			String allowedStatuses = allowedStatusesSet.stream().map(CellVisibleStatus::name).collect(Collectors.joining(", "));
			throw new InvalidCommandException(String.format("Interaction only supported for cell statuses: %s", allowedStatuses));
		}
		visibleStatus = newStatus;
	}

	public void affectNeighbourCells(Minefield minefield) {
		Position position = mineCell.getPosition();
		for (Position.Direction direction: Position.Direction.values()) {
			Position neighbourPosition = position.getRelativePosition(direction);
			if (neighbourPosition.isWithinBounds(config)) {
				int x = neighbourPosition.getX();
				int y = neighbourPosition.getY();
				cellsGrid[x][y] = mineCell.affectNeighbourCell(cellsGrid[x][y]);
			}
		}
	}


}
