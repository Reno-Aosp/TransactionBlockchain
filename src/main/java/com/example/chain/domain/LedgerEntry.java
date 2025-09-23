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
public abstract class LedgerEntry {
    private String ref;        // encapsulated
    private String payload;    // encapsulated
    private Instant createdAt; // encapsulated

    // Constructor
    protected LedgerEntry(String ref, String payload) {
        this.ref = Objects.requireNonNull(ref, "ref is required");
        this.payload = Objects.requireNonNull(payload, "payload is required");
        this.createdAt = Instant.now();
    }

    // Encapsulation: getters/setters
    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = Objects.requireNonNull(ref); }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = Objects.requireNonNull(payload); }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = Objects.requireNonNull(createdAt); }

    /** Overridable label for type; children override. */
    public String getType() { return "LEDGER"; }

    /** Force children to validate their content. */
    public abstract void validate() throws ValidationException;

    @Override public String toString() {
        return "%s{ref=%s, createdAt=%s}".formatted(getType(), ref, createdAt);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LedgerEntry that)) return false;
        return Objects.equals(ref, that.ref) &&
               Objects.equals(payload, that.payload) &&
               Objects.equals(createdAt, that.createdAt) &&
               Objects.equals(getType(), that.getType());
    }

    @Override public int hashCode() {
        return Objects.hash(ref, payload, createdAt, getType());
    }
}
