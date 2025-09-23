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

@Service
@RequiredArgsConstructor
public class LogService {
    private final PublicTxLog contract;

    // Demonstrates List usage (buffer before on-chain write)
    private final List<LedgerEntry> pending = new ArrayList<>();

    /** Validate then buffer an entry. */
    public void buffer(LedgerEntry entry) throws ValidationException {
        entry.validate(); // may throw our custom exception
        pending.add(entry);
    }

    /** Flush one entry to chain (override behavior depends on child type via polymorphism). */
    public TransactionReceipt flushOne() throws Exception {
        if (pending.isEmpty()) throw new IllegalStateException("No entries to flush");
        LedgerEntry next = pending.remove(0);
        // Encapsulation: use getters to access data
        return contract.log(next.getRef(), next.getPayload()).send();
    }

    /** Flush all buffered entries to chain; returns tx hashes. */
    public List<String> flushAll() throws Exception {
        List<String> hashes = new ArrayList<>();
        while (!pending.isEmpty()) {
            hashes.add(flushOne().getTransactionHash());
        }
        return hashes;
    }

    /** Read-only view of buffer. */
    public List<LedgerEntry> listBuffered() {
        return Collections.unmodifiableList(pending);
    }
}
