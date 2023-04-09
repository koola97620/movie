package com.example.movie.pay.infra;

import com.example.movie.pay.app.PaymentRequest;
import com.example.movie.pay.app.PaymentResultResponse;
import com.example.movie.pay.app.PaymentSuccessRequest;
import com.example.movie.pay.domain.PaymentBase;
import com.example.movie.pay.domain.PaymentMethodType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaopayApiService {

    private final KakaopayApiClient apiClient;
    private final String redirectUrlHost;
    private final String adminKey;
    private final String cid;

    public KakaopayApiService(KakaopayApiClient apiClient, @Value("${kakaopay.redirect-url-host}") String redirectUrlHost,
                              @Value("${kakaopay.admin-key}") String adminKey, @Value("${kakaopay.cid}") String cid) {
        this.apiClient = apiClient;
        this.redirectUrlHost = redirectUrlHost;
        this.adminKey = adminKey;
        this.cid = cid;
    }

    public KakaopayReadyApiResponse ready(PaymentRequest req, Long payId) {
        KakaopayReadyApiRequest readyApiRequest = KakaopayReadyApiRequest.builder()
                .cid(cid)
                .partner_order_id(String.valueOf(req.orderId()))
                .partner_user_id(String.valueOf(req.userId()))
                .item_name(req.productName())
                .quantity(req.quantity())
                .total_amount(req.amount())
                .approval_url(createApprovalUrl(payId))
                .cancel_url(redirectUrlHost + "/partnersvc/kakaopay/payment_kakaopay/v1/payment_cancel?payId=" + payId)
                .fail_url(redirectUrlHost + "/partnersvc/kakaopay/payment_kakaopay/v1/payment_fail?payId=" + payId)
                .payment_method_type(choosePaymentMethodType(req.paymentMethodType()))
                .install_month(req.installMonth())
                .tax_free_amount(req.taxFreeAmount())
                .build();
        KakaopayReadyApiResponse respopnse = apiClient.ready(adminKey, readyApiRequest);
        return respopnse;
    }

    private String createApprovalUrl(Long payId) {
        return redirectUrlHost + "/partnersvc/kakaopay/payment_kakaopay/v1/payment_success" + "?payId=" + payId;
    }

    private String choosePaymentMethodType(String paymentMethodType) {
        return PaymentMethodType.of(paymentMethodType).getCode();
    }

    public PaymentResultResponse approve(PaymentSuccessRequest req, PaymentBase paymentBase) {
        KakaopayApproveApiRequest request = KakaopayApproveApiRequest.builder()
                .cid(cid)
                .tid(paymentBase.getTid())
                .partner_order_id(String.valueOf(paymentBase.getOrderId()))
                .partner_user_id(String.valueOf(paymentBase.getUserId()))
                .total_amount(paymentBase.getPayAmount())
                .pg_token(req.pg_token())
                .build();
        return apiClient.approve(adminKey, request);
    }
}
