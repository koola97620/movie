package com.example.movie.pay.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum KakaopayPaymentStatus {
    CREATED(1001),
    SUCCESS(1002),
    FAIL(1003),
    CANCEL(1004)
    ;

    private Integer code;

    KakaopayPaymentStatus(Integer code) {
        this.code = code;
    }

    public static KakaopayPaymentStatus ofCode(Integer code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("잘못된 KakaopayPaymentStatus 코드 입니다.code: " + code));
    }
}
