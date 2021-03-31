package com.minesweeper.domain.cell

import com.minesweeper.datafixture.AdjacentToMineCellDataFixture
import com.minesweeper.datafixture.EmptyCellDataFixture
import com.minesweeper.datafixture.MineCellDataFixture
import com.minesweeper.domain.InteractionResult

class EmptyCellTest extends CellTest {

	def "will update cell when marked with red flag"() {
		given:
		def cell = new EmptyCellDataFixture().basic().cell

		when:
		def result = cell.markRedFlag()

		then:
		cell.visibleStatus == CellVisibleStatus.RED_FLAG
		result == InteractionResult.RED_FLAG_ADDED_INCORRECTLY
	}

	def "will return false when checking if it contains a mine"() {
		given:
		def cell = new EmptyCellDataFixture().basic().cell

		when:
		def result = cell.containsMine()

		then:
		!result
	}

	def "will return true when checking if it must be revealed while exploring"() {
		given:
		def cell = new EmptyCellDataFixture().basic().cell

		when:
		def result = cell.mustBeRevealedWhileExploring()

		then:
		result
	}

	def "will not affect adjacent mines"() {
		given:
		def cell = new EmptyCellDataFixture().basic().cell

		when:
		def result = cell.increaseAdjacentMineCount(neighbourCell)

		then:
		result == neighbourCell

		where:
		neighbourCell										| _
		new EmptyCellDataFixture().basic().cell				| _
		new MineCellDataFixture().basic().cell				| _
		new AdjacentToMineCellDataFixture().basic().cell	| _
	}

	def "will be converted to AdjacentToMineCell when increasing mine count"() {
		given:
		def cell = new EmptyCellDataFixture().basic().cell

		when:
		def result = cell.autoIncreaseMineCount()

		then:
		result in AdjacentToMineCell
		(result as AdjacentToMineCell).adjacentMinesCount == 1
	}
}
