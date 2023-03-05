package com.example.movie.infra;

import com.example.movie.app.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaopayApiService {

    private final KakaopayApiClient apiClient;
    private final String adminKey;
    private final String cid;

    public KakaopayApiService(KakaopayApiClient apiClient, @Value("${kakaopay.admin-key}") String adminKey, @Value("${kakaopay.cid}") String cid) {
        this.apiClient = apiClient;
        this.adminKey = adminKey;
        this.cid = cid;
    }

    public KakaopayReadyApiResponse ready(PaymentRequest req) {
        KakaopayReadyApiRequest readyApiRequest = KakaopayReadyApiRequest.builder()
                .cid(cid)
                .partner_order_id(String.valueOf(req.orderId()))
                .partner_user_id(String.valueOf(req.userId()))
                .item_name(req.productName())
                .quantity(req.quantity())
                .total_amount(req.amount())
                .approval_url("http://localhost:7001/extsvc/success")
                .cancel_url("http://localhost:7001/extsvc/success")
                .fail_url("http://localhost:7001/extsvc/success")
                .payment_method_type(choosePaymentMethodType(req.paymentMethodType()))
                .install_month(req.installMonth())
                .build();
        KakaopayReadyApiResponse response = apiClient.ready(adminKey, readyApiRequest);
        return response;
    }

    private String choosePaymentMethodType(String paymentMethodType) {
        return PaymentMethodType.of(paymentMethodType).name();
    }
}
