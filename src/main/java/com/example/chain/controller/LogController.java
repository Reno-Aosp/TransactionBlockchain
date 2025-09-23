package com.example.chain.controller;

import com.example.chain.domain.EntryFactory;
import com.example.chain.domain.LedgerEntry;
import com.example.chain.domain.ValidationException;
import com.example.chain.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService service;

    @PostMapping("/{type}")
    public Map<String, Object> buffer(
            @PathVariable String type,
            @RequestParam String ref,
            @RequestParam String payload
    ) throws ValidationException {
        LedgerEntry entry = EntryFactory.create(type, ref, payload);
        service.buffer(entry); // may throw ValidationException
        return Map.of("status", "buffered", "type", entry.getType(), "ref", entry.getRef());
    }

    @PostMapping("/flushOne")
    public Map<String, Object> flushOne() throws Exception {
        var rc = service.flushOne();
        return Map.of("txHash", rc.getTransactionHash());
    }

    @PostMapping("/flushAll")
    public Map<String, Object> flushAll() throws Exception {
        List<String> hashes = service.flushAll();
        return Map.of("count", hashes.size(), "txHashes", hashes);
    }

    @GetMapping("/buffer")
    public List<LedgerEntry> bufferList() {
        return service.listBuffered();
    }
}
