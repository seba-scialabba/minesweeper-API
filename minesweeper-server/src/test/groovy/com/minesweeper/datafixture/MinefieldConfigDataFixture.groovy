package com.minesweeper.datafixture

import lombok.Getter

import com.minesweeper.domain.minefield.MinefieldConfig
import com.minesweeper.domain.minefield.MinefieldType
import com.minesweeper.domain.minefield.squared.SquaredMinefieldConfig

@Getter
class MinefieldConfigDataFixture {
	MinefieldConfig minefieldConfig

	MinefieldConfigDataFixture basicSquared() {
		minefieldConfig = new SquaredMinefieldConfig()
		type(MinefieldType.SQUARED)
		minesCount(10)
	}

	MinefieldConfigDataFixture type(MinefieldType type) {
		minefieldConfig.type = type
		this
	}

	MinefieldConfigDataFixture minesCount(long minesCount) {
		minefieldConfig.minesCount = minesCount
		this
	}

}
