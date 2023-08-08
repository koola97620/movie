package com.example.pay.app;

import lombok.Builder;

public record PaymentSuccessResponse(
        String tid,
        String aid,
        Long orderId,
        Integer userId
) {
    @Builder
    public PaymentSuccessResponse {}
}
