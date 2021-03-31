package com.minesweeper.datafixture.domain.minefield

import lombok.Getter

import com.minesweeper.domain.minefield.MinefieldConfig

@Getter
class MinefieldConfigDataFixture {
	MinefieldConfig minefieldConfig

	MinefieldConfigDataFixture basic() {
		minefieldConfig = new MinefieldConfig()
		height(5)
		width(10)
		minesCount(10)
	}

	MinefieldConfigDataFixture height(int height) {
		minefieldConfig.height = height
		this
	}

	MinefieldConfigDataFixture width(int width) {
		minefieldConfig.width = width
		this
	}

	MinefieldConfigDataFixture minesCount(long minesCount) {
		minefieldConfig.minesCount = minesCount
		this
	}

}
