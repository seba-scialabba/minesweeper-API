package com.minesweeper.domain.cell;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public enum CellVisibilityStatus {
	HIDDEN,
	VISIBLE,
	MARKED_MINE,
	MARKED_QUESTION;

	public static final Set<CellVisibilityStatus> SUPPORTS_MARKING_RED_FLAG = ImmutableSet.of(HIDDEN, MARKED_QUESTION);
}
