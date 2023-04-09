package com.example.movie.pay.domain;

public record Amount(
        Integer total,
        Integer tax_free,
        Integer vat,
        Integer point,
        Integer discount,
        Integer green_deposit
) {
}
