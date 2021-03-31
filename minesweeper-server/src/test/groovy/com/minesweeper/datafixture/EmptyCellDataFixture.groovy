package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.EmptyCell

class EmptyCellDataFixture extends CellDataFixture<EmptyCell> {
	@Override
	protected Cell createCell() {
		new EmptyCell()
	}
}
