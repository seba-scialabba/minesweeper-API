package com.minesweeper.datafixture.domain.cell

import com.minesweeper.domain.cell.AdjacentToMineCell
import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibleStatus

class AdjacentToMineCellDataFixture extends CellDataFixture<AdjacentToMineCell> {

	@Override
	protected Cell createCell() {
		new AdjacentToMineCell()
	}

	AdjacentToMineCellDataFixture basic() {
		super.basic()
		adjacentMinesCount(1)
		this
	}

	AdjacentToMineCellDataFixture basicWithNeighbours() {
		basic()
		/*
		1	1	0
		M	1	0
		1	1	0
		 */
		neighbours([
				new AdjacentToMineCellDataFixture().basic().visibleStatus(CellVisibleStatus.VISIBLE).cell,
				new EmptyCellDataFixture().basic().cell,
				new EmptyCellDataFixture().basic().cell,
				new EmptyCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new MineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell
		])
		this
	}

	AdjacentToMineCellDataFixture adjacentMinesCount(int adjacentMinesCount) {
		cell.adjacentMinesCount = adjacentMinesCount
		this
	}
}
