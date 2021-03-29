package com.minesweeper.domain;

import lombok.Builder;
import lombok.Data;

import com.minesweeper.domain.minefield.Minefield;

@Data
@Builder
public class Session {
	private Account account;
	private Minefield minefield;
	private SessionStatus status;
	private Statistics statistics;
}
