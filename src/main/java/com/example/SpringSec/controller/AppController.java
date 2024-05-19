package com.example.SpringSec.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {
    @GetMapping("/admin/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/test/authtest")
    public ResponseEntity<?> authtest() {
        return ResponseEntity.ok("pong");
    }
}
