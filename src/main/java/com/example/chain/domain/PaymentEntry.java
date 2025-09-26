package com.example.chain.domain;

public class PaymentEntry extends LedgerEntry {        // INHERITANCE (extends = child class)
    
    public PaymentEntry(String ref, String payload) {  // CONSTRUCTOR
        super(ref, payload);                           // CALLING PARENT CONSTRUCTOR
    }

    @Override public String getType() {                // OVERRIDING parent method
        return "PAYMENT"; 
    }

    @Override public void validate() throws ValidationException { // OVERRIDING + THROWING EXCEPTION
        if (!getRef().startsWith("INV-")) {
            throw new ValidationException("Payment ref must start with INV-"); // THROWING CUSTOM EXCEPTION
        }
        if (!getPayload().contains("\"amount\"")) {
            throw new ValidationException("Payment payload must include \"amount\""); // THROWING CUSTOM EXCEPTION
        }
    }
}
