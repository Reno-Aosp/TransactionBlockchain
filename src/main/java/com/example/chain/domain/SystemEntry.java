package com.example.chain.domain;

public class SystemEntry extends LedgerEntry {         // INHERITANCE (extends = child class)
    
    public SystemEntry(String ref, String payload) {   // CONSTRUCTOR
        super(ref, payload);                           // CALLING PARENT CONSTRUCTOR
    }

    @Override public String getType() {                // OVERRIDING parent method
        return "SYSTEM"; 
    }

    @Override public void validate() throws ValidationException { // OVERRIDING + THROWING EXCEPTION
        if (!getRef().startsWith("SYS-")) {
            throw new ValidationException("System ref must start with SYS-"); // THROWING CUSTOM EXCEPTION
        }
    }
}
