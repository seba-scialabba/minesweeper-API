package com.minesweeper.domain.minefield;

import java.awt.*;
import java.util.Optional;
import java.util.stream.IntStream;

import lombok.Getter;

import com.minesweeper.domain.cell.Cell;
import com.minesweeper.domain.cell.EmptyCell;
import com.minesweeper.domain.cell.MineCell;

@Getter
public class Minefield {
	private MinefieldConfig config;
	private Cell[][] cellsGrid;

	public Minefield(MinefieldConfig config) {
		config.validate();
		this.config = config;
		cellsGrid = new Cell[config.getWidth()][config.getHeight()];
	}

	public void initialize() {
		fillWithEmptyCells();
		positionRandomMines();
	}

	private void fillWithEmptyCells() {
		IntStream.range(0, config.getWidth()).parallel()
			.forEach(x -> IntStream.range(0, config.getHeight()).parallel()
				.forEach(y -> createAndLinkCells(x, y))
			);
	}

	private void createAndLinkCells(int x, int y) {
		Cell currentCell = Optional.ofNullable(cellsGrid[x][y]).orElse(new EmptyCell());
		cellsGrid[x][y] = currentCell;
		SyncGridCellsFunction<Cell, Integer, Integer, Integer> syncGridCellsFunction =
			(cell, xIndex, yIndex, neighbourIndex) -> linkGridCellToCellNeighboursWithinBounds(cell, xIndex, yIndex, neighbourIndex);
		syncGridCellToCellNeighbours(syncGridCellsFunction, x, y, currentCell);
	}

	private void linkGridCellToCellNeighboursWithinBounds(Cell currentCell, int x, int y, int neighbourIndex) {
		if (x >= 0 && x < config.getWidth() && y >= 0 && y < config.getHeight()) {
			Cell neighbourCell = Optional.ofNullable(cellsGrid[x][y]).orElse(new EmptyCell());
			currentCell.setNeighbour(neighbourIndex, neighbourCell);
			cellsGrid[x][y] = neighbourCell;
		}
	}

	private void positionRandomMines() {
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
		Cell gridCell = cellsGrid[x][y];
		if (gridCell.containsMine()) {
			return false;
		}
		Cell replacedCell = gridCell.replaceWith(new MineCell());
		cellsGrid[x][y] = replacedCell;

		SyncGridCellsFunction<Cell, Integer, Integer, Integer> syncGridCellsFunction =
			(cell, xIndex, yIndex, neighbourIndex) -> linkNeighbourCellsBackToGridWithinBounds(cell, xIndex, yIndex, neighbourIndex);
		syncGridCellToCellNeighbours(syncGridCellsFunction, x, y, replacedCell);
		return true;
	}

	private void syncGridCellToCellNeighbours(SyncGridCellsFunction<Cell, Integer, Integer, Integer> syncGridCellsFunction, int x, int y, Cell cell) {
		syncGridCellsFunction.apply(cell, x, y - 1, 0);
		syncGridCellsFunction.apply(cell, x + 1, y - 1, 1);
		syncGridCellsFunction.apply(cell, x + 1, y, 2);
		syncGridCellsFunction.apply(cell, x + 1, y + 1, 3);
		syncGridCellsFunction.apply(cell, x, y + 1, 4);
		syncGridCellsFunction.apply(cell, x - 1, y + 1, 5);
		syncGridCellsFunction.apply(cell, x - 1, y, 6);
		syncGridCellsFunction.apply(cell, x - 1, y - 1, 7);
	}

	private void linkNeighbourCellsBackToGridWithinBounds(Cell cell, int x, int y, int neighbourIndex) {
		if (x >= 0 && x < config.getWidth() && y >= 0 && y < config.getHeight()) {
			cellsGrid[x][y] = cell.getNeighbours()[neighbourIndex];
		}
	}

	private Point getRandomMineCoordinates() {
		int x = (int) (Math.random() * config.getWidth());
		int y = (int) (Math.random() * config.getHeight());
		return new Point(x, y);
	}

	@FunctionalInterface
	private interface SyncGridCellsFunction<currentCell, x, y, neighbourIndex> {
		void apply(Cell currentCell, Integer x, Integer y, Integer neighbourIndex);
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
