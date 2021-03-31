package com.minesweeper.datafixture.domain.cell

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibleStatus
import com.minesweeper.domain.cell.EmptyCell

class EmptyCellDataFixture extends CellDataFixture<EmptyCell> {
	@Override
	protected Cell createCell() {
		new EmptyCell()
	}

	EmptyCellDataFixture basic() {
		super.basic()
		this
	}

	EmptyCellDataFixture basicWithNeighbours() {
		basic()
		/*
		1	0	0
		1	0	0
		1	0	0
		 */
		neighbours([
				new EmptyCellDataFixture().basic().visibleStatus(CellVisibleStatus.VISIBLE).cell,
				new EmptyCellDataFixture().basic().cell,
				new EmptyCellDataFixture().basic().cell,
				new EmptyCellDataFixture().basic().cell,
				new EmptyCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell
		])
		this
	}
}
