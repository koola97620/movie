package com.example.movie.infra;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public enum PaymentMethodType {
    CARD("CARD"),
    MONEY("MONEY"),
    ALL(null);

    private String code;

    PaymentMethodType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PaymentMethodType of(String type) {
        if (ObjectUtils.isEmpty(type)) {
            return ALL;
        }

        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
