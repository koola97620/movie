package com.example.movie.pay.domain;

import java.util.Arrays;

public enum PgType {
    KAKAOPAY(1001,"카카오페이")
    ;

    private Integer code;
    private String desc;

    PgType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PgType ofCode(Integer code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("잘못된 PgType 코드 입니다.code: " + code));
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
