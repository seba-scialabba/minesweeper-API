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
		adjacentMinesCount(1)
		this
	}

	AdjacentToMineCellDataFixture adjacentMinesCount(int adjacentMinesCount) {
		cell.adjacentMinesCount = adjacentMinesCount
		this
	}
}
