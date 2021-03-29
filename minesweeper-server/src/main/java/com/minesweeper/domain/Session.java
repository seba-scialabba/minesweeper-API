package com.minesweeper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Session {
	private Account account;
	private Minefield minefield;
	private SessionStatus status;
	private Statistics statistics;
}
