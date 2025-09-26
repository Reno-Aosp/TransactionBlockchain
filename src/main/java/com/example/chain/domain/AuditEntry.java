package com.example.chain.domain;

public class AuditEntry extends LedgerEntry {          // INHERITANCE (extends = child class)
    
    public AuditEntry(String ref, String payload) {    // CONSTRUCTOR
        super(ref, payload);                           // CALLING PARENT CONSTRUCTOR
    }

    @Override public String getType() {                // OVERRIDING parent method
        return "AUDIT"; 
    }

    @Override public void validate() throws ValidationException { // OVERRIDING + THROWING EXCEPTION
        if (getPayload().isBlank()) {
            throw new ValidationException("Audit payload cannot be blank"); // THROWING CUSTOM EXCEPTION
        }
    }
}
