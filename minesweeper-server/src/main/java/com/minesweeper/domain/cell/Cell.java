package com.minesweeper.domain.cell;

import java.util.List;

public abstract class Cell {
	protected List<Cell> neighbours;
	protected CellVisibilityStatus visibilityStatus = CellVisibilityStatus.HIDDEN;

}
