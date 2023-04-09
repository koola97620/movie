package com.example.movie.pay.app;

import com.example.movie.pay.domain.KakaopayPaymentStatus;
import com.example.movie.pay.domain.PaymentBase;
import com.example.movie.pay.infra.KakaopayApiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentProcessService {

    private final PaymentBaseService paymentBaseService;
    private final KakaopayApiService kakaopayApiService;

    public PaymentProcessService(PaymentBaseService paymentBaseService, KakaopayApiService kakaopayApiService) {
        this.paymentBaseService = paymentBaseService;
        this.kakaopayApiService = kakaopayApiService;
    }

    @Transactional
    public PaymentSuccessResponse success(PaymentSuccessRequest req) {
        PaymentBase payment = paymentBaseService.findByPayId(req.payId());
        PaymentResultResponse approveResponse = kakaopayApiService.approve(req, payment);
        payment.updatePayResult(KakaopayPaymentStatus.SUCCESS, approveResponse);
        return PaymentSuccessResponse.builder()
                .tid(approveResponse.tid())
                .aid(approveResponse.aid())
                .orderId(Long.valueOf(approveResponse.partner_order_id()))
                .userId(Integer.parseInt(approveResponse.partner_user_id()))
                .build();
    }

    public String fail(Long payId) {
        paymentBaseService.updateStatus(payId, KakaopayPaymentStatus.FAIL);
        return "결제 실패했습니다.";
    }

    public String cancel(Long payId) {
        paymentBaseService.updateStatus(payId, KakaopayPaymentStatus.CANCEL);
        return "결제 취소했습니다.";
    }
}
