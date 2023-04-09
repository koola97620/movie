package com.example.movie.pay.app;

import com.example.movie.pay.domain.PaymentBase;
import com.example.movie.pay.infra.KakaopayApiService;
import com.example.movie.pay.infra.KakaopayReadyApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentRequestService {

    private final PaymentBaseService paymentBaseService;
    private final KakaopayApiService apiService;

    public PaymentRequestService(PaymentBaseService paymentBaseService, KakaopayApiService apiService) {
        this.paymentBaseService = paymentBaseService;
        this.apiService = apiService;
    }

    @Transactional
    public PaymentResponse paymentRequest(PaymentRequest req) {
        // validate
        PaymentBase paymentBase = paymentBaseService.create(req);
        KakaopayReadyApiResponse readyResponse = apiService.ready(req, paymentBase.getPayId());
        paymentBase.updateTid(readyResponse.tid());

        return PaymentResponse.builder()
                .payId(paymentBase.getPayId())
                .tid(readyResponse.tid())
                .orderId(req.orderId())
                .payAmount(req.amount())
                .pcUrl(readyResponse.next_redirect_pc_url())
                .build();
    }
}
