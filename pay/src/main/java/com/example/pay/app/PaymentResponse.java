package com.example.pay.app;

import lombok.Builder;

public record PaymentResponse(
        Long payId,
        String tid,
        Long orderId,
        Integer payAmount,
        String pcUrl
) {
    @Builder
    public PaymentResponse {}
}
