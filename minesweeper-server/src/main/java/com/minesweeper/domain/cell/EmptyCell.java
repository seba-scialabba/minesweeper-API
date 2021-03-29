package com.minesweeper.domain.cell;

import java.util.Objects;

import com.minesweeper.domain.InteractionResult;

public class EmptyCell extends Cell {
	@Override
	public InteractionResult explore() {
		revealWhileExploring(this);
		return InteractionResult.CELL_REVEALED;
	}

	protected boolean mustRevealWhileExploring() {
		return true;
	}

	private void revealWhileExploring(Cell cell) {
		if (!cell.mustRevealWhileExploring()) {
			return;
		}
		cell.setVisibleStatus(CellVisibleStatus.VISIBLE);
		neighbours.parallelStream()
			.filter(Objects::nonNull)
			.forEach(this::revealWhileExploring);
	}
}
