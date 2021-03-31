package com.minesweeper.datafixture

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.MineCell

class MineCellDataFixture extends CellDataFixture<MineCell> {
	@Override
	protected Cell createCell() {
		new MineCell()
	}
}
