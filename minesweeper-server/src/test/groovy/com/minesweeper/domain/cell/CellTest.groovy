package com.minesweeper.domain.cell

import com.minesweeper.datafixture.EmptyCellDataFixture
import com.minesweeper.datafixture.MineCellDataFixture
import com.minesweeper.datafixture.MineNeighbourCellDataFixture
import com.minesweeper.domain.InteractionResult
import com.minesweeper.exception.InvalidCommandException
import spock.lang.Specification

class CellTest extends Specification {

	def "will throw exception if cell can't be marked with red flag"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibilityStatus(status).cell

		when:
		cell.markRedFlag()

		then:
		def exception = thrown(InvalidCommandException)
		exception.message == "Marking with red flag only supported for cell statuses: HIDDEN, MARKED_QUESTION"

		where:
		status | _
		CellVisibilityStatus.VISIBLE | _
		CellVisibilityStatus.MARKED_MINE | _
	}

	def "will return result when marking the cell with red flag"() {
		when:
		def result = cell.markRedFlag()

		then:
		result == expectedResult

		where:
		cell                                            | expectedResult
		new EmptyCellDataFixture().basic().cell         | InteractionResult.RED_FLAG_ADDED_INCORRECTLY
		new MineNeighbourCellDataFixture().basic().cell | InteractionResult.RED_FLAG_ADDED_INCORRECTLY
		new MineCellDataFixture().basic().cell          | InteractionResult.RED_FLAG_ADDED_CORRECTLY
	}
}
