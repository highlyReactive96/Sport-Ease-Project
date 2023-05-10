package com.example.demo.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class SportsNotFoundException extends RuntimeException {
    public SportsNotFoundException(Long id) {
        super("Could not find sports with id: " + id);
    }

	public SportsNotFoundException(String string) {
		super(string);
	}
}

