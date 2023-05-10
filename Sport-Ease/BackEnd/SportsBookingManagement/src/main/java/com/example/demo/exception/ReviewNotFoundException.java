package com.example.demo.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long academyId) {
        super("Reviews not found for academy with id " + academyId);
    }

	public ReviewNotFoundException(String string) {
		super(string);
	}
}