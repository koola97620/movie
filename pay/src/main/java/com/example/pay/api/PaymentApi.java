package com.example.pay.api;

import com.example.pay.app.PaymentRequest;
import com.example.pay.app.PaymentRequestService;
import com.example.pay.common.ApiResponse;
import com.example.pay.exception.KakaopayApiCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentApi {

    private final PaymentRequestService service;

    public PaymentApi(PaymentRequestService service) {
        this.service = service;
    }

    @PostMapping("/intsvc/homepage/payment_kakaopay/v1/payment")
    public ApiResponse<?> paymentRequest(@RequestBody PaymentRequest req) {
        return ApiResponse.of(KakaopayApiCode.SUCCESS.getCode(), "정상적으로 처리하였습니다.", service.paymentRequest(req));
    }
}
