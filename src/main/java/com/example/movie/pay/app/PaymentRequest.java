package com.example.movie.pay.app;

import lombok.Builder;

public record PaymentRequest(
        Long orderId,
        Integer userId,
        String productName,
        Integer quantity,
        Integer amount,
        String paymentMethodType,
        Integer installMonth
) {
    @Builder
    public PaymentRequest {}
}
