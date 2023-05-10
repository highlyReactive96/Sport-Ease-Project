package com.example.demo.exception;

public class PlayerNotFoundException extends RuntimeException {
//	 /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(Long id) {
	        super("Player not found with id: " + id);
	    }

	public PlayerNotFoundException(String string) {
		super(string);
	}
}
