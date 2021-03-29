package com.minesweeper.domain.cell;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public enum CellVisibleStatus {
	HIDDEN,
	VISIBLE,
	RED_FLAG,
	QUESTION;

	public static final Set<CellVisibleStatus> SUPPORTS_MARKING_RED_FLAG = ImmutableSet.of(HIDDEN, QUESTION);
	public static final Set<CellVisibleStatus> SUPPORTS_MARKING_QUESTION = ImmutableSet.of(HIDDEN, RED_FLAG);
}
