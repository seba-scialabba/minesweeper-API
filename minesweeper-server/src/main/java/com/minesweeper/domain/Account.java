package com.minesweeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class Account {
	private String id;
	private String name;
}
