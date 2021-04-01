package com.minesweeper.domain.cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import com.minesweeper.domain.minefield.MinefieldConfig;

@AllArgsConstructor(staticName = "of")
@Data
public class Position {
	private int x;
	private int y;

	public boolean isWithinBounds(MinefieldConfig config) {
		return x >= 0 && x < config.getWidth() && y >= 0 && y < config.getHeight();
	}

	public static Position getRandomPositionWithinBounds(MinefieldConfig config) {
		int x = (int) (Math.random() * config.getWidth());
		int y = (int) (Math.random() * config.getHeight());
		return Position.of(x, y);
	}

	public Position getRelativePosition(Direction direction) {
		return Position.of(x + direction.getRelativeX(), y + direction.getRelativeY());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position position = (Position) o;
		return x == position.x &&
			y == position.y;
	}

	@Override
	public int hashCode() {
		return x * 10_000 + y;
	}

	@Getter
	@AllArgsConstructor
	public enum Direction {
		UPPER		(0, -1),
		UPPER_RIGHT	(1, -1),
		RIGHT		(1, 0),
		LOWER_RIGHT	(1, 1),
		LOWER		(0, 1),
		LOWER_LEFT	(-1, 1),
		LEFT		(-1, 0),
		UPPER_LEFT	(-1, -1);

		private int relativeX;
		private int relativeY;
	}
}
