package com.minesweeper.domain.minefield

import com.minesweeper.datafixture.domain.minefield.MinefieldConfigDataFixture
import com.minesweeper.exception.InvalidConfigurationException
import spock.lang.Specification

class MinefieldConfigTest extends Specification {

	def "will throw an exception if configuration is not valid"() {
		when:
		configuration.validate()

		then:
		def exception = thrown(InvalidConfigurationException)
		exception.message == expectedMessage

		where:
		configuration | expectedMessage
		new MinefieldConfigDataFixture().basic().height(-1).minefieldConfig | "Minimum grid side is 2 x 2"
		new MinefieldConfigDataFixture().basic().height(1).width(1).minefieldConfig | "Minimum grid side is 2 x 2"
		new MinefieldConfigDataFixture().basic().minesCount(0).minefieldConfig | "There must be at least one mine"
		new MinefieldConfigDataFixture().basic().height(2).width(2).minesCount(4).minefieldConfig | "Mines count must be lower than the total space of the grid"
	}
}
