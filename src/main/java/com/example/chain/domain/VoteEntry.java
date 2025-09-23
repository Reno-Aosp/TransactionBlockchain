package com.example.chain.domain;

public class VoteEntry extends LedgerEntry {
    public VoteEntry(String ref, String payload) { super(ref, payload); }

    @Override public String getType() { return "VOTE"; } // overriding

    @Override public void validate() throws ValidationException {
        if (!getRef().startsWith("ELECT-")) {
            throw new ValidationException("Vote ref must start with ELECT-");
        }
        if (getPayload().length() > 2048) {
            throw new ValidationException("Vote payload too large");
        }
    }
}
