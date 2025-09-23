package com.example.chain.domain;

public final class EntryFactory {
    private EntryFactory() {}

    public static LedgerEntry create(String type, String ref, String payload) throws ValidationException {
        String t = (type == null ? "" : type.trim()).toUpperCase();
        return switch (t) {
            case "VOTE"    -> new VoteEntry(ref, payload);
            case "PAYMENT" -> new PaymentEntry(ref, payload);
            case "AUDIT"   -> new AuditEntry(ref, payload);
            case "SYSTEM"  -> new SystemEntry(ref, payload);
            case "GENERIC", "" -> new GenericEntry(ref, payload);
            default -> throw new ValidationException("Unknown entry type: " + type);
        };
    }
}
