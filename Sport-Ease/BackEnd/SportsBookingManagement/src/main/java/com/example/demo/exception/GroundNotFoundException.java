package com.example.demo.exception;

public class GroundNotFoundException extends RuntimeException {
    public GroundNotFoundException(Long id) {
        super("Ground not found with id: " + id);
    }
}
