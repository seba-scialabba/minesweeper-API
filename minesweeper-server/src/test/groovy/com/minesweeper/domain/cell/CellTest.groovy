package com.minesweeper.domain.cell


import com.minesweeper.datafixture.domain.cell.EmptyCellDataFixture
import com.minesweeper.exception.InvalidCommandException
import spock.lang.Specification

abstract class CellTest extends Specification {

	def "will throw exception if cell can't be marked with red flag"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(status).cell

		when:
		cell.markRedFlag()

		then:
		def exception = thrown(InvalidCommandException)
		exception.message == "Interaction only supported for cell statuses: HIDDEN, QUESTION"

		where:
		status                     | _
		CellVisibleStatus.VISIBLE  | _
		CellVisibleStatus.RED_FLAG | _
	}

	def "will throw exception if cell can't remove red flag"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(status).cell

		when:
		cell.removeRedFlag()

		then:
		def exception = thrown(InvalidCommandException)
		exception.message == "Interaction only supported for cell statuses: RED_FLAG"

		where:
		status						| _
		CellVisibleStatus.VISIBLE	| _
		CellVisibleStatus.QUESTION	| _
		CellVisibleStatus.HIDDEN	| _
	}

	def "will update cell when red flag is removed"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(CellVisibleStatus.RED_FLAG).cell

		when:
		cell.removeRedFlag()

		then:
		cell.visibleStatus == CellVisibleStatus.HIDDEN
	}

	def "will throw exception if cell can't be marked with question"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(status).cell

		when:
		cell.markQuestion()

		then:
		def exception = thrown(InvalidCommandException)
		exception.message == "Interaction only supported for cell statuses: HIDDEN, RED_FLAG"

		where:
		status                     | _
		CellVisibleStatus.VISIBLE  | _
		CellVisibleStatus.QUESTION | _
	}

	def "will update cell when marked with question"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(status).cell

		when:
		cell.markQuestion()

		then:
		cell.visibleStatus == CellVisibleStatus.QUESTION

		where:
		status						| _
		CellVisibleStatus.HIDDEN	| _
		CellVisibleStatus.RED_FLAG	| _
	}

	def "will throw exception if cell can't remove question mark"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(status).cell

		when:
		cell.removeQuestion()

		then:
		def exception = thrown(InvalidCommandException)
		exception.message == "Interaction only supported for cell statuses: QUESTION"

		where:
		status						| _
		CellVisibleStatus.VISIBLE	| _
		CellVisibleStatus.RED_FLAG	| _
		CellVisibleStatus.HIDDEN	| _
	}

	def "will update cell when question is removed"() {
		given:
		def cell = new EmptyCellDataFixture().basic().visibleStatus(CellVisibleStatus.QUESTION).cell

		when:
		cell.removeQuestion()

		then:
		cell.visibleStatus == CellVisibleStatus.HIDDEN
	}
}
