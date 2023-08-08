package com.example.pay.domain;

import java.util.Arrays;

public enum PaymentStatus {
    READY(1001),
    SEND_TMS(1002),
    OPEN_PAYMENT(1003),
    SELECT_METHOD(1004),
    ARS_WAITING(1005),
    AUTH_PASSWORD(1006),
    ISSUED_SID(1007),
    SUCCESS_PAYMENT(1008),
    CANCEL_PAYMENT(1009),
    FAIL_AUTH_PASSWORD(1010),
    QUIT_PAYMENT(1011),
    FAIL_PAYMENT(1012)
    ;

    private Integer code;
    private String msg;

    PaymentStatus(Integer code) {
        this.code = code;
    }

    public static PaymentStatus ofCode(Integer code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("잘못된 PaymentStatus 코드 입니다.code: " + code));
    }

    public Integer getCode() {
        return code;
    }
}
