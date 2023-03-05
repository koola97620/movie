package com.example.movie.infra;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public enum PaymentMethodType {
    CARD,
    MONEY,
    ALL;

    public static PaymentMethodType of(String type) {
        if (ObjectUtils.isEmpty(type)) {
            return ALL;
        }

        return Arrays.stream(values())
                .filter(e -> e.name().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
