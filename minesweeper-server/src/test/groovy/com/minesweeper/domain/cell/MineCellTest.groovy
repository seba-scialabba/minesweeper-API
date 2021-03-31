package com.minesweeper.domain.cell

import com.minesweeper.datafixture.AdjacentToMineCellDataFixture
import com.minesweeper.datafixture.EmptyCellDataFixture
import com.minesweeper.datafixture.MineCellDataFixture
import com.minesweeper.domain.InteractionResult

class MineCellTest extends CellTest {

	def "will update cell when marked with red flag"() {
		given:
		def cell = new MineCellDataFixture().basic().cell

		when:
		def result = cell.markRedFlag()

		then:
		cell.visibleStatus == CellVisibleStatus.RED_FLAG
		result == InteractionResult.RED_FLAG_ADDED_CORRECTLY
	}

	def "will return true when checking if it contains a mine"() {
		given:
		def cell = new MineCellDataFixture().basic().cell

		when:
		def result = cell.containsMine()

		then:
		result
	}

	def "will return false when checking if it must be revealed while exploring"() {
		given:
		def cell = new MineCellDataFixture().basic().cell

		when:
		def result = cell.mustBeRevealedWhileExploring()

		then:
		!result
	}

	def "will affect adjacent mines count"() {
		given:
		def cell = new MineCellDataFixture().basic().cell

		when:
		def result = cell.increaseAdjacentMineCount(neighbourCell)

		then:
		result in expectedCellClass
		if (expectedCellClass == AdjacentToMineCell) {
			assert (result as AdjacentToMineCell).getAdjacentMinesCount() ==  expectedMineCount
		}

		where:
		[neighbourCell, expectedCellClass, expectedMineCount] << affectedAdjacentMinesDataPipe()
	}

	private affectedAdjacentMinesDataPipe() {
		def emptyCell = new EmptyCellDataFixture().basic().cell
		def mineCell = new MineCellDataFixture().basic().cell
		def adjacentToOneMineCell = new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(1).cell
		[
		        [emptyCell, AdjacentToMineCell, 1],
				[adjacentToOneMineCell, AdjacentToMineCell, 2],
				[mineCell, MineCell, null]
		]
	}

	def "will not be affected by increasing mine count"() {
		given:
		def cell = new MineCellDataFixture().basic().cell

		when:
		def result = cell.autoIncreaseMineCount()

		then:
		result in MineCell
		result == cell
	}

	def "will replace cell with a mine"(Cell originalCell, List<Cell> expectedNeighbours) {
		given:
		def mineCell = new MineCellDataFixture().basicWithNeighbours().cell

		when:
		def result = originalCell.replaceWith(mineCell)

		then:
		result in MineCell
		result.neighbours == expectedNeighbours

		where:
		[originalCell, expectedNeighbours] << replaceWithDataPipe()
	}

	private replaceWithDataPipe() {
		def originalEmptyCell = new EmptyCellDataFixture().basicWithNeighbours().cell
		def originalAdjacentToMineCell = new AdjacentToMineCellDataFixture().basicWithNeighbours().cell

		def expectedNeighboursFromEmptyCell = [
				new AdjacentToMineCellDataFixture().basic().visibleStatus(CellVisibleStatus.VISIBLE).cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell
		]
		def expectedNeighboursFromAdjacentToMineCell = [
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).visibleStatus(CellVisibleStatus.VISIBLE).cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell,
				new MineCellDataFixture().basic().cell,
				new AdjacentToMineCellDataFixture().basic().adjacentMinesCount(2).cell
		]
		[
				/*
				1	0	0		2	1	1
				1	0	0	->	2	M	1
				1	0	0		2	1	1
				 */
		        [originalEmptyCell, expectedNeighboursFromEmptyCell],

				/*
				1	1	0		2	2	1
				M	1	0	->	M	M	1
				1	1	0		2	2	1
		 		*/
				[originalAdjacentToMineCell, expectedNeighboursFromAdjacentToMineCell]
		]
	}
}
