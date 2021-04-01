package com.minesweeper.domain.cell;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.minesweeper.domain.InteractionResult;
import com.minesweeper.domain.minefield.Minefield;

@Getter
@NoArgsConstructor
public class AdjacentToMineCell extends Cell {
	private int adjacentMinesCount = 0;

	public AdjacentToMineCell(Cell cell) {
		super(cell);
	}

	@Override
	public InteractionResult explore(Minefield minefield) {
		visibleStatus = CellVisibleStatus.VISIBLE;
		return InteractionResult.CELL_REVEALED;
	}

	@Override
	public boolean containsMine() {
		return false;
	}

	@Override
	public Cell affectNeighbourCell(Cell neighbourCell) {
		// Does not affect other cells
		return neighbourCell;
	}

	@Override
	protected Cell incrementMineCount() {
		adjacentMinesCount++;
		return this;
	}

	@Override
	protected boolean mustBeRevealedWhileExploring() {
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(adjacentMinesCount);
	}
}
