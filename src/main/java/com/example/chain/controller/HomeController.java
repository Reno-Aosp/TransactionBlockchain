package com.example.chain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "Blockchain Transaction Demo API - Available endpoints: /api/buffered, /api/buffer, /api/flush";
    }
}