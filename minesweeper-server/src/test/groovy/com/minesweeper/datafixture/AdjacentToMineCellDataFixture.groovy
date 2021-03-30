package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.AdjacentToMineCell

class AdjacentToMineCellDataFixture extends CellDataFixture {
	@Override
	protected Cell createCell() {
		new AdjacentToMineCell()
	}
}
