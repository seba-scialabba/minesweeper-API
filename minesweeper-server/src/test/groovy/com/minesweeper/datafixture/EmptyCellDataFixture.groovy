package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.EmptyCell

class EmptyCellDataFixture extends CellDataFixture<EmptyCell> {
	@Override
	protected Cell createCell() {
		new EmptyCell()
	}

	EmptyCellDataFixture basic() {
		super.basic()
		/*
		1	0	0
		1	0	0
		1	0	0
		 */
		neighbours([
				new EmptyCellDataFixture().cell,			// Upper
				new EmptyCellDataFixture().cell,			// Upper-right
				new EmptyCellDataFixture().cell,			// Right
				new EmptyCellDataFixture().cell,			// Lower-right
				new AdjacentToMineCellDataFixture().cell,	// Lower
				new AdjacentToMineCellDataFixture().cell,	// Lower-left
				new AdjacentToMineCellDataFixture().cell,	// Left
				new AdjacentToMineCellDataFixture().cell	// Upper-left
		])
		this
	}
}
