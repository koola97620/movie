package com.example.movie.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectUrlApi {

    @GetMapping("/extsvc/success")
    public ResponseEntity<?> success() {
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("/extsvc/fail")
    public ResponseEntity<?> fail() {
        return ResponseEntity.ok("FAIL");
    }

    @GetMapping("/extsvc/cancel")
    public ResponseEntity<?> cancel() {
        return ResponseEntity.ok("CANCEL");
    }
}
