package com.minesweeper.domain.cell;

import java.util.Objects;

import lombok.NoArgsConstructor;

import com.minesweeper.domain.InteractionResult;

@NoArgsConstructor
public class EmptyCell extends Cell {

	public EmptyCell(Cell cell) {
		super(cell);
	}

	@Override
	public InteractionResult explore() {
		revealWhileExploring(this);
		return InteractionResult.CELL_REVEALED;
	}

	@Override
	public boolean containsMine() {
		return false;
	}

	@Override
	protected boolean mustBeRevealedWhileExploring() {
		return true;
	}

	@Override
	protected Cell increaseAdjacentMineCount(Cell neighbourCell) {
		// Does not affect adjacent mines count
		return neighbourCell;
	}

	@Override
	protected Cell autoIncreaseMineCount() {
		Cell newCell = new AdjacentToMineCell(this);
		newCell.autoIncreaseMineCount();
		return newCell;
	}

	private void revealWhileExploring(Cell cell) {
		if (!cell.mustBeRevealedWhileExploring()) {
			return;
		}
		cell.setVisibleStatus(CellVisibleStatus.VISIBLE);
		neighbours.parallelStream()
			.filter(Objects::nonNull)
			.forEach(this::revealWhileExploring);
	}
}
