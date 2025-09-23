package com.example.chain.domain;

public class SystemEntry extends LedgerEntry {
    public SystemEntry(String ref, String payload) { super(ref, payload); }

    @Override public String getType() { return "SYSTEM"; }

    @Override public void validate() throws ValidationException {
        if (!getRef().startsWith("SYS-")) {
            throw new ValidationException("System ref must start with SYS-");
        }
    }
}
