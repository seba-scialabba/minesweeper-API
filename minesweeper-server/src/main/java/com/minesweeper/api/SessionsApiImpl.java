package com.minesweeper.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionsApiImpl implements SessionsApi {

	@Override
	public ResponseEntity<Void> createSession() {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
