package com.example.chain.domain;

public class GenericEntry extends LedgerEntry {
    public GenericEntry(String ref, String payload) { super(ref, payload); }

    @Override public String getType() { return "GENERIC"; }

    @Override public void validate() throws ValidationException {
        if (getRef().isBlank()) {
            throw new ValidationException("Ref cannot be blank");
        }
    }
}
