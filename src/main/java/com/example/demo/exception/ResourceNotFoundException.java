package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String message;
    
    public ResourceNotFoundException() {
        super("Resource Not Found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message= message;
    }
}
