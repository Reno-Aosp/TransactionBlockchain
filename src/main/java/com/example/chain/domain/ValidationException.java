package com.example.chain.domain;

/** Custom checked exception for validation failures. */
public class ValidationException extends Exception {
    public ValidationException(String message) { super(message); }
}
