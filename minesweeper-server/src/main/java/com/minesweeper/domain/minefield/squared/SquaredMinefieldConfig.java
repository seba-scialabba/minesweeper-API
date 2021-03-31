package com.minesweeper.domain.minefield.squared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.minesweeper.domain.minefield.MinefieldConfig;

/**
 * Adds dimensions config for a squared minefield
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SquaredMinefieldConfig extends MinefieldConfig {
	private int width;
	private int height;
}
