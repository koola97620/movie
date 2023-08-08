package com.example.pay.app;

public record PaymentSuccessRequest(
        Long payId,
        String pg_token
) {

}
