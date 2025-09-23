package com.example.chain.domain;

public class PaymentEntry extends LedgerEntry {
    public PaymentEntry(String ref, String payload) { super(ref, payload); }

    @Override public String getType() { return "PAYMENT"; }

    @Override public void validate() throws ValidationException {
        if (!getRef().startsWith("INV-")) {
            throw new ValidationException("Payment ref must start with INV-");
        }
        if (!getPayload().contains("\"amount\"")) {
            throw new ValidationException("Payment payload must include \"amount\"");
        }
    }
}
