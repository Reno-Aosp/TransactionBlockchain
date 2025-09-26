package com.example.chain.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * Parent (abstract) class demonstrating:
 * - Encapsulation via private fields + getters/setters
 * - Constructor
 * - Overridable/abstract methods
 * - equals/hashCode/toString overrides
 */
public abstract class LedgerEntry {                    // SUPERCLASS/PARENT CLASS
    private String ref;        // ENCAPSULATION (private field)
    private String payload;    // ENCAPSULATION (private field)
    private Instant createdAt; // ENCAPSULATION (private field)

    // Constructor
    protected LedgerEntry(String ref, String payload) { // CONSTRUCTOR (protected for inheritance)
        this.ref = Objects.requireNonNull(ref, "ref is required");
        this.payload = Objects.requireNonNull(payload, "payload is required");
        this.createdAt = Instant.now();
    }

    // ENCAPSULATION: getters/setters
    public String getRef() { return ref; }                              // GETTER (encapsulation)
    public void setRef(String ref) { this.ref = Objects.requireNonNull(ref); } // SETTER (encapsulation)

    public String getPayload() { return payload; }                     // GETTER (encapsulation)
    public void setPayload(String payload) { this.payload = Objects.requireNonNull(payload); } // SETTER (encapsulation)

    public Instant getCreatedAt() { return createdAt; }                // GETTER (encapsulation)
    public void setCreatedAt(Instant createdAt) { this.createdAt = Objects.requireNonNull(createdAt); } // SETTER (encapsulation)

    /** Overridable label for type; children override. */
    public String getType() { return "LEDGER"; }        // OVERRIDABLE METHOD (for inheritance)

    /** Force children to validate their content. */
    public abstract void validate() throws ValidationException; // ABSTRACT METHOD + THROWING EXCEPTION

    @Override public String toString() {                // OVERRIDING (Object's toString)
        return "%s{ref=%s, createdAt=%s}".formatted(getType(), ref, createdAt);
    }

    @Override public boolean equals(Object o) {         // OVERRIDING (Object's equals)
        if (this == o) return true;
        if (!(o instanceof LedgerEntry that)) return false;
        return Objects.equals(ref, that.ref) &&
               Objects.equals(payload, that.payload) &&
               Objects.equals(createdAt, that.createdAt) &&
               Objects.equals(getType(), that.getType());
    }

    @Override public int hashCode() {                   // OVERRIDING (Object's hashCode)
        return Objects.hash(ref, payload, createdAt, getType());
    }
}
