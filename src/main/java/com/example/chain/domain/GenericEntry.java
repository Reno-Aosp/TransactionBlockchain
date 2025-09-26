package com.example.chain.domain;

public class GenericEntry extends LedgerEntry {        // INHERITANCE (extends = child class)
    
    public GenericEntry(String ref, String payload) {  // CONSTRUCTOR
        super(ref, payload);                           // CALLING PARENT CONSTRUCTOR
    }

    @Override public String getType() {                // OVERRIDING parent method
        return "GENERIC"; 
    }

    @Override public void validate() throws ValidationException { // OVERRIDING + THROWING EXCEPTION
        if (getRef().isBlank()) {
            throw new ValidationException("Ref cannot be blank"); // THROWING CUSTOM EXCEPTION
        }
    }
}
