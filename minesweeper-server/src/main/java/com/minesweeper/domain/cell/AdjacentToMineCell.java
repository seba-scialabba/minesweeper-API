package com.minesweeper.domain.cell;

import lombok.Getter;

import com.minesweeper.domain.InteractionResult;

@Getter
public class AdjacentToMineCell extends Cell {
	private int adjacentMinesCount = 0;

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
}
