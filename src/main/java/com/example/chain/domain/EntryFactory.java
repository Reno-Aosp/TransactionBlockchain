package com.example.chain.domain;

public final class EntryFactory {                      // FINAL CLASS (cannot be inherited)
    private EntryFactory() {}                          // PRIVATE CONSTRUCTOR (prevents instantiation)

    public static LedgerEntry create(String type, String ref, String payload) throws ValidationException { // STATIC METHOD + THROWING EXCEPTION
        String t = (type == null ? "" : type.trim()).toUpperCase();
        return switch (t) {                            // POLYMORPHISM (returns different child types)
            case "VOTE"    -> new VoteEntry(ref, payload);      // CREATING CHILD OBJECT
            case "PAYMENT" -> new PaymentEntry(ref, payload);   // CREATING CHILD OBJECT
            case "AUDIT"   -> new AuditEntry(ref, payload);     // CREATING CHILD OBJECT
            case "SYSTEM"  -> new SystemEntry(ref, payload);    // CREATING CHILD OBJECT
            case "GENERIC", "" -> new GenericEntry(ref, payload); // CREATING CHILD OBJECT
            default -> throw new ValidationException("Unknown entry type: " + type); // THROWING CUSTOM EXCEPTION
        };
    }
}
