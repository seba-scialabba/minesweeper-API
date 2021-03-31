package com.minesweeper.domain.minefield.squared;

import java.awt.*;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.minesweeper.domain.cell.EmptyCell;
import com.minesweeper.domain.cell.Cell;
import com.minesweeper.domain.cell.MineCell;
import com.minesweeper.domain.minefield.Minefield;

@Component("SQUARED")
public class SquaredMinefield extends Minefield<SquaredMinefieldConfig> {
	private Cell[][] minefield;

	public SquaredMinefield(SquaredMinefieldConfig config) {
		super(config);
		minefield = new Cell[config.getWidth()][config.getHeight()];
	}

	@Override
	protected void fillWithEmptyCells() {
		IntStream.range(0, config.getWidth()).parallel()
			.forEach(x -> IntStream.range(0, config.getHeight()).parallel()
				.forEach(y -> minefield[x][y] = new EmptyCell())
			);
	}

	@Override
	protected void positionRandomMines() {
		int positionedMines = 0;
		while (positionedMines < config.getMinesCount()) {
			Point mineCoordinates = getRandomMineCoordinates();
			if (positionMineInCoordinates(mineCoordinates)) {
				positionedMines++;
			}
		}
	}

	private boolean positionMineInCoordinates(Point mineCoordinates) {
		int x = (int) mineCoordinates.getX();
		int y = (int) mineCoordinates.getY();
		Cell cell = minefield[x][y];
		if (cell.containsMine()) {
			return false;
		}
		minefield[x][y] = cell.replaceWith(new MineCell());
		return true;
	}

	private Point getRandomMineCoordinates() {
		int x = (int) (Math.random() * config.getWidth());
		int y = (int) (Math.random() * config.getHeight());
		return new Point(x, y);
	}
}
