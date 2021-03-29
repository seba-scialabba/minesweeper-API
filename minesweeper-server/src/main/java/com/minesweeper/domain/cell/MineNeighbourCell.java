package com.minesweeper.domain.cell;

import com.minesweeper.domain.InteractionResult;

public class MineNeighbourCell extends Cell {
	@Override
	public InteractionResult explore() {
		visibleStatus = CellVisibleStatus.VISIBLE;
		return InteractionResult.CELL_REVEALED;
	}
}
