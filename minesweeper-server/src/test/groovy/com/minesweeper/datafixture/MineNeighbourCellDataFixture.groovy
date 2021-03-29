package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.MineNeighbourCell

class MineNeighbourCellDataFixture extends CellDataFixture {
	@Override
	protected Cell createCell() {
		new MineNeighbourCell()
	}
}
