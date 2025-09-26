package com.example.chain.domain;

/** Custom checked exception for validation failures. */
public class ValidationException extends Exception {   // INHERITANCE (extends Exception)
    public ValidationException(String message) {       // CONSTRUCTOR
        super(message);                                // CALLING PARENT CONSTRUCTOR
    }
}
