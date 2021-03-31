package com.minesweeper.datafixture.domain.cell

import lombok.Getter

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibleStatus

@Getter
abstract class CellDataFixture<C extends Cell> {
	C cell

	CellDataFixture() {
		cell = createCell()
	}

	protected abstract Cell createCell()

	CellDataFixture basic() {
		neighbours([])
		visibleStatus(CellVisibleStatus.HIDDEN)
		this
	}

	CellDataFixture neighbours(List<Cell> neighbours) {
		cell.neighbours = neighbours
		this
	}
	CellDataFixture visibleStatus(CellVisibleStatus visibleStatus) {
		cell.visibleStatus = visibleStatus
		this
	}
}
