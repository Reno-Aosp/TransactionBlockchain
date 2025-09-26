package com.example.chain.domain;

public class VoteEntry extends LedgerEntry {           // INHERITANCE (extends = child class)
    
    public VoteEntry(String ref, String payload) {     // CONSTRUCTOR
        super(ref, payload);                           // CALLING PARENT CONSTRUCTOR
    }

    @Override public String getType() {                // OVERRIDING parent method
        return "VOTE"; 
    }

    @Override public void validate() throws ValidationException { // OVERRIDING + THROWING EXCEPTION
        if (!getRef().startsWith("ELECT-")) {
            throw new ValidationException("Vote ref must start with ELECT-"); // THROWING CUSTOM EXCEPTION
        }
        if (getPayload().length() > 2048) {
            throw new ValidationException("Vote payload too large"); // THROWING CUSTOM EXCEPTION
        }
    }
}
