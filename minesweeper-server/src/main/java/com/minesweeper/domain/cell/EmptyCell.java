package com.minesweeper.domain.cell;

import java.util.stream.IntStream;

import lombok.NoArgsConstructor;

import com.minesweeper.domain.InteractionResult;
import com.minesweeper.domain.minefield.Minefield;

@NoArgsConstructor
public class EmptyCell extends Cell {

	public EmptyCell(Cell cell) {
		super(cell);
	}

	@Override
	public InteractionResult explore(Minefield minefield) {
		revealWhileExploring(this, minefield);
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
	protected boolean mustBeRevealedWhileExploring() {
		return true;
	}

	@Override
	protected Cell incrementMineCount() {
		Cell newCell = new AdjacentToMineCell(this);
		newCell.incrementMineCount();
		return newCell;
	}

	private void revealWhileExploring(Cell cell, Minefield minefield) {
		if (!cell.mustBeRevealedWhileExploring()) {
			return;
		}
		cell.setVisibleStatus(CellVisibleStatus.VISIBLE);
		IntStream.range(0, neighbours.length).parallel()
			.filter(index -> neighbours[index] != null)
			.forEach(index -> revealWhileExploring(neighbours[index]));
	}

	@Override
	public String toString() {
		return "0";
	}
}
