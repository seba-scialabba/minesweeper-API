package com.minesweeper.domain.minefield

import com.minesweeper.datafixture.domain.minefield.MinefieldConfigDataFixture
import com.minesweeper.domain.cell.Cell
import com.minesweeper.domain.cell.CellVisibleStatus
import spock.lang.Specification

class MinefieldTest extends Specification {

	def "will create and initialize a random minefield given config"() {
		given:
		def minefieldConfig = new MinefieldConfigDataFixture().basic()
				.height(2)
				.width(2)
				.minesCount(2)
				.minefieldConfig
		def minefield = new Minefield(minefieldConfig)

		when:
		minefield.initialize()

		then:
		minefield.cellsGrid
		minefield.cellsGrid.length == minefieldConfig.width
		minefield.cellsGrid[0].length == minefieldConfig.height
		def resultingMineCount = 0
		for (Cell[] cellRow: minefield.cellsGrid) {
			for (Cell cell: cellRow) {
				assert cell
				cell.visibleStatus == CellVisibleStatus.HIDDEN
				if (cell.containsMine()) {
					resultingMineCount++
				}
			}
		}
		resultingMineCount == minefieldConfig.minesCount
	}
}
