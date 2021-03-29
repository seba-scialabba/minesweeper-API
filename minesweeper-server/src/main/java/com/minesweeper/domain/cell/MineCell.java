package com.minesweeper.domain.cell;

import com.minesweeper.domain.InteractionResult;

public class MineCell extends Cell {
	@Override
	public InteractionResult explore() {
		visibleStatus = CellVisibleStatus.VISIBLE;
		return InteractionResult.EXPLOSION;
	}

	@Override
	public InteractionResult markRedFlag() {
		super.markRedFlag();
		return InteractionResult.RED_FLAG_ADDED_CORRECTLY;
	}
}
