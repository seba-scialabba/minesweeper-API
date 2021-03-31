package com.minesweeper.domain.cell;

import lombok.NoArgsConstructor;

import com.minesweeper.domain.InteractionResult;

@NoArgsConstructor
public class MineCell extends Cell {

	public MineCell(Cell cell) {
		super(cell);
	}

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

	@Override
	public boolean containsMine() {
		return true;
	}

	@Override
	protected boolean mustBeRevealedWhileExploring() {
		return false;
	}

	@Override
	protected Cell increaseAdjacentMineCount(Cell neighbourCell) {
		return neighbourCell.autoIncreaseMineCount();
	}

	@Override
	protected Cell autoIncreaseMineCount() {
		// Does not carry an adjacent mine count
		return this;
	}

	@Override
	public String toString() {
		return "M";
	}
}
