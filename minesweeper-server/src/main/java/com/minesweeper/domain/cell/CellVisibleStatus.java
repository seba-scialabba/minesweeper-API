package com.minesweeper.domain.cell;

import java.util.Set;

import com.google.common.collect.Sets;

public enum CellVisibleStatus {
	HIDDEN,
	VISIBLE,
	RED_FLAG,
	QUESTION;

	public static final Set<CellVisibleStatus> SUPPORTS_MARKING_RED_FLAG = Sets.immutableEnumSet(HIDDEN, QUESTION);
	public static final Set<CellVisibleStatus> SUPPORTS_MARKING_QUESTION = Sets.immutableEnumSet(HIDDEN, RED_FLAG);
}
