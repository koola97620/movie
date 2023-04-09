package com.example.movie.pay.app;

public record PaymentSuccessRequest(
        Long payId,
        String pg_token
) {

}
