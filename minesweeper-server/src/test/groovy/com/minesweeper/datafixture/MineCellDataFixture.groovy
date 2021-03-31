package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.MineCell

class MineCellDataFixture extends CellDataFixture<MineCell> {
	@Override
	protected Cell createCell() {
		new MineCell()
	}

	MineCellDataFixture basic() {
		super.basic()
		/*
		2	2	1
		M	M	1
		2	2	1
		 */
		neighbours([
				new AdjacentToMineCellDataFixture().adjacentMinesCount(2).cell,	// Upper
				new AdjacentToMineCellDataFixture().cell,						// Upper-right
				new AdjacentToMineCellDataFixture().cell,						// Right
				new AdjacentToMineCellDataFixture().cell,						// Lower-right
				new AdjacentToMineCellDataFixture().adjacentMinesCount(2).cell,	// Lower
				new AdjacentToMineCellDataFixture().adjacentMinesCount(2).cell,	// Lower-left
				new MineCellDataFixture().cell,									// Left
				new AdjacentToMineCellDataFixture().adjacentMinesCount(2).cell	// Upper-left
		])
		this
	}
}
