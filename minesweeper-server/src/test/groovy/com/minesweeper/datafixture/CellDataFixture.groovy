package com.minesweeper.datafixture

import lombok.Getter

import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibilityStatus

@Getter
abstract class CellDataFixture {
	Cell cell

	CellDataFixture() {
		cell = createCell()
	}

	protected abstract Cell createCell()

	CellDataFixture basic() {
		neighbours([])
		visibilityStatus(CellVisibilityStatus.HIDDEN)
		this
	}

	CellDataFixture neighbours(List<Cell> neighbours) {
		cell.neighbours = neighbours
		this
	}
	CellDataFixture visibilityStatus(CellVisibilityStatus visibilityStatus) {
		cell.visibilityStatus = visibilityStatus
		this
	}
}
