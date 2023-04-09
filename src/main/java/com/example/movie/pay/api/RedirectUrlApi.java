package com.example.movie.pay.api;

import com.example.movie.pay.app.PaymentProcessService;
import com.example.movie.pay.app.PaymentSuccessRequest;
import com.example.movie.pay.common.ApiResponse;
import com.example.movie.pay.exception.KakaopayApiCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectUrlApi {

    private final PaymentProcessService service;

    public RedirectUrlApi(PaymentProcessService service) {
        this.service = service;
    }

    @GetMapping("/partnersvc/kakaopay/payment_kakaopay/v1/payment_success")
    public ApiResponse<?> success(PaymentSuccessRequest req) {
        return ApiResponse.of(KakaopayApiCode.SUCCESS.getCode(), "정상적으로 처리하였습니다.", service.success(req));
    }

    @GetMapping("/partnersvc/kakaopay/payment_kakaopay/v1/payment_fail")
    public ApiResponse<?> fail(Long payId) {
        return ApiResponse.of(KakaopayApiCode.SUCCESS.getCode(), "결제에 실패했습니다.", service.fail(payId));
    }

    @GetMapping("/partnersvc/kakaopay/payment_kakaopay/v1/payment_cancel")
    public ApiResponse<?> cancel(Long payId) {
        return ApiResponse.of(KakaopayApiCode.SUCCESS.getCode(), "결제를 취소 했습니다.", service.cancel(payId));
    }
}
