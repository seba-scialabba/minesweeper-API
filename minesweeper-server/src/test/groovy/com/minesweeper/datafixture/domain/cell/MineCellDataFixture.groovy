package com.minesweeper.datafixture.domain.cell

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibleStatus
import com.minesweeper.domain.cell.MineCell

class MineCellDataFixture extends CellDataFixture<MineCell> {
	@Override
	protected Cell createCell() {
		new MineCell()
	}

	MineCellDataFixture basic() {
		super.basic()
		this
	}

	MineCellDataFixture basicWithNeighbours() {
		basic()
		/*
		2	2	1
		M	M	1
		2	2	1
		 */
		neighbours([
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).visibleStatus(CellVisibleStatus.VISIBLE).cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new MineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell
		])
		this
	}
}
