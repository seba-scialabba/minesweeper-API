package com.minesweeper.domain.cell;

import lombok.Getter;

import com.minesweeper.domain.InteractionResult;

@Getter
public class AdjacentToMineCell extends Cell {
	private long adjacentMinesCount = 0;

	@Override
	public InteractionResult explore() {
		visibleStatus = CellVisibleStatus.VISIBLE;
		return InteractionResult.CELL_REVEALED;
	}

	@Override
	public void increaseMineProximityCount() {
		adjacentMinesCount++;
	}
}
