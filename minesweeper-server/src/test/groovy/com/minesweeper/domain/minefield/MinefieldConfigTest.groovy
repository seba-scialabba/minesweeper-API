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
		new MinefieldConfigDataFixture().basic().height(-1).minefieldConfig | "Height and width must be at least 2"
		new MinefieldConfigDataFixture().basic().height(1).width(1).minefieldConfig | "Height and width must be at least 2"
		new MinefieldConfigDataFixture().basic().height(100_000).width(5).minefieldConfig | "Height and width must be at most 10,000"
		new MinefieldConfigDataFixture().basic().height(100).width(200_000_000).minefieldConfig | "Height and width must be at most 10,000"
		new MinefieldConfigDataFixture().basic().minesCount(0).minefieldConfig | "There must be at least one mine"
		new MinefieldConfigDataFixture().basic().height(2).width(2).minesCount(4).minefieldConfig | "Mines count must be lower than the total space of the grid"
	}
}
