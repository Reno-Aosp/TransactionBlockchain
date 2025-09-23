package com.example.chain.domain;

public class AuditEntry extends LedgerEntry {
    public AuditEntry(String ref, String payload) { super(ref, payload); }

    @Override public String getType() { return "AUDIT"; }

    @Override public void validate() throws ValidationException {
        if (getPayload().isBlank()) {
            throw new ValidationException("Audit payload cannot be blank");
        }
    }
}
