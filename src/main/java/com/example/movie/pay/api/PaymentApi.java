package com.example.movie.pay.api;

import com.example.movie.pay.app.PaymentRequest;
import com.example.movie.pay.app.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentApi {

    private final PaymentService service;

    public PaymentApi(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/intsvc/homepage/payment_kakaopay/v1/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentRequest req) {
        return ResponseEntity.ok(service.payment(req));
    }
}
