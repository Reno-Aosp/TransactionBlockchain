package com.example.chain.service;

import com.example.chain.PublicTxLog; // web3j generated wrapper
import com.example.chain.domain.LedgerEntry;
import com.example.chain.domain.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service                                              // SPRING ANNOTATION
@RequiredArgsConstructor                              // LOMBOK ANNOTATION (dependency injection)
public class LogService {
    private final PublicTxLog contract;               // ENCAPSULATION (private field) + DEPENDENCY INJECTION

    private final List<LedgerEntry> pending = new ArrayList<>(); // ENCAPSULATION + POLYMORPHISM (can hold different child types)

    /** Validate then buffer an entry. */
    public void buffer(LedgerEntry entry) throws ValidationException { // POLYMORPHISM (accepts any child type) + THROWING EXCEPTION
        entry.validate();                             // POLYMORPHISM (calls appropriate child's validate method)
        pending.add(entry);                          // POLYMORPHISM (stores any child type)
    }

    /** Flush one entry to chain. */
    public TransactionReceipt flushOne() throws Exception { // THROWING EXCEPTION
        if (pending.isEmpty()) throw new IllegalStateException("No entries to flush"); // THROWING EXCEPTION
        LedgerEntry next = pending.remove(0);        // POLYMORPHISM (could be any child type)
        return contract.log(next.getRef(), next.getPayload()).send(); // ENCAPSULATION (using getters)
    }

    /** Flush all buffered entries to chain; returns tx hashes. */
    public List<String> flushAll() throws Exception { // THROWING EXCEPTION
        List<String> hashes = new ArrayList<>();
        while (!pending.isEmpty()) {
            hashes.add(flushOne().getTransactionHash()); // MAY THROW EXCEPTION
        }
        return hashes;
    }

    /** Read-only view of buffer. */
    public List<LedgerEntry> listBuffered() {         // POLYMORPHISM (returns different child types)
        return Collections.unmodifiableList(pending); // ENCAPSULATION (read-only access)
    }
}
