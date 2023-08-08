package com.example.pay.app;

import lombok.Builder;

public record PaymentRequest(
        Long orderId,
        Integer userId,
        String productName,
        Integer quantity,
        Integer amount,
        String paymentMethodType,
        Integer installMonth,
        Integer taxFreeAmount
) {
    @Builder
    public PaymentRequest {}
}
