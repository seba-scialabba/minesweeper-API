package com.minesweeper.domain.minefield;

import java.util.stream.IntStream;

import lombok.Getter;

import com.minesweeper.domain.cell.Cell;
import com.minesweeper.domain.cell.EmptyCell;
import com.minesweeper.domain.cell.MineCell;
import com.minesweeper.domain.cell.Position;

@Getter
public class Minefield {
	private MinefieldConfig config;
	private Cell[][] cellsGrid;

	public Minefield(MinefieldConfig config) {
		config.validate();
		this.config = config;
		cellsGrid = new Cell[config.getWidth()][config.getHeight()];
	}

	/**
	 * Creates a mine field positioning the mines randomly
	 */
	public void initialize() {
		fillWithEmptyCells();
		positionRandomMines();
	}

	private void fillWithEmptyCells() {
		IntStream.range(0, config.getWidth()).parallel()
			.forEach(x -> IntStream.range(0, config.getHeight()).parallel()
				.forEach(y -> cellsGrid[x][y] = new EmptyCell())
			);
	}

	private void positionRandomMines() {
		int positionedMines = 0;
		while (positionedMines < config.getMinesCount()) {
			Position mineCoordinates = Position.getRandomPositionWithinBounds(config);
			if (setMineInCoordinates(mineCoordinates)) {
				positionedMines++;
			}
		}
	}

	private boolean setMineInCoordinates(Position mineCoordinates) {
		int x = mineCoordinates.getX();
		int y = mineCoordinates.getY();
		if (cellsGrid[x][y].containsMine()) {
			return false;
		}
		cellsGrid[x][y] = new MineCell();
		cellsGrid[x][y].affectNeighbourCells(this);
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < config.getWidth(); i++) {
			for (int j = 0; j < config.getHeight(); j++) {
				builder.append(cellsGrid[i][j].toString()).append("\t");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
