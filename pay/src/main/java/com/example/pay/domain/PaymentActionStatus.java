package com.example.pay.domain;

import java.util.Arrays;

public enum PaymentActionStatus {
    PAYMENT(1001,"결제성공"),
    CANCEL(1002,"결제취소"),
    ISSUED_SID(1003,"SID발급")
    ;

    private Integer code;
    private String desc;

    PaymentActionStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PaymentActionStatus ofCode(Integer code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("잘못된 PaymentResultType 코드 입니다.code: " + code));
    }

    public Integer getCode() {
        return code;
    }
}
