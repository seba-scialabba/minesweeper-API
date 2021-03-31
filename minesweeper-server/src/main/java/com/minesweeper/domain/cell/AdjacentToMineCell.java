package com.minesweeper.domain.cell;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.minesweeper.domain.InteractionResult;

@Getter
@NoArgsConstructor
public class AdjacentToMineCell extends Cell {
	private int adjacentMinesCount = 0;

	public AdjacentToMineCell(Cell cell) {
		super(cell);
	}

	@Override
	public InteractionResult explore() {
		visibleStatus = CellVisibleStatus.VISIBLE;
		return InteractionResult.CELL_REVEALED;
	}

	@Override
	public boolean containsMine() {
		return false;
	}

	@Override
	protected boolean mustBeRevealedWhileExploring() {
		return false;
	}

	@Override
	protected Cell increaseAdjacentMineCount(Cell neighbourCell) {
		// Does not affect adjacent mines count
		return neighbourCell;
	}

	@Override
	protected Cell autoIncreaseMineCount() {
		adjacentMinesCount++;
		return this;
	}

	@Override
	public String toString() {
		return String.valueOf(adjacentMinesCount);
	}
}
