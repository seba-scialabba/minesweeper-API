package com.minesweeper.domain.minefield

import org.springframework.beans.factory.BeanFactory

import com.minesweeper.datafixture.MinefieldConfigDataFixture
import spock.lang.Specification

class MinefieldFactoryTest extends Specification {
	BeanFactory beanFactory = Mock()
	MinefieldFactory minefieldFactory

	def setup() {
		minefieldFactory = new MinefieldFactory(beanFactory: beanFactory)
	}

	def "will use beanFactory to get specific implementation of minefield"() {
		given:
		def minefieldConfig = new MinefieldConfigDataFixture().basicSquared().minefieldConfig

		when:
		minefieldFactory.createMinefield(minefieldConfig)

		then:
		1 * beanFactory.getBean(minefieldConfig.getType().name(), minefieldConfig)
	}
}
