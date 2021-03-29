package com.minesweeper.domain.minefield.squared;

import org.springframework.stereotype.Component;

import com.minesweeper.domain.minefield.Minefield;
import com.minesweeper.domain.minefield.MinefieldConfig;

@Component("SQUARED")
public class SquaredMinefield extends Minefield {
	public SquaredMinefield(MinefieldConfig config) {
		super(config);
	}

	@Override
	public void initialize() {

	}
}
