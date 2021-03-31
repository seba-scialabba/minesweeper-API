package com.minesweeper.datafixture

import com.minesweeper.domain.cell.AdjacentToMineCell
import com.minesweeper.domain.cell.Cell

class AdjacentToMineCellDataFixture extends CellDataFixture<AdjacentToMineCell> {

	@Override
	protected Cell createCell() {
		new AdjacentToMineCell()
	}

	AdjacentToMineCellDataFixture basic() {
		super.basic()
		/*
		1	1	0
		M	1	0
		1	1	0
		 */
		neighbours([
		        new AdjacentToMineCellDataFixture().cell,	// Upper
				new EmptyCellDataFixture().cell,			// Upper-right
				new EmptyCellDataFixture().cell,			// Right
				new EmptyCellDataFixture().cell,			// Lower-right
				new AdjacentToMineCellDataFixture().cell,	// Lower
				new AdjacentToMineCellDataFixture().cell,	// Lower-left
				new MineCellDataFixture().cell,				// Left
				new AdjacentToMineCellDataFixture().cell	// Upper-left
		])
		adjacentMinesCount(1)
		this
	}

	AdjacentToMineCellDataFixture adjacentMinesCount(int adjacentMinesCount) {
		cell.adjacentMinesCount = adjacentMinesCount
		this
	}
}
