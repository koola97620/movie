package com.example.movie.pay.exception;

public enum KakaopayApiCode {
    SUCCESS("00", "성공"),
    PARAM_EXCEPTION("99",""),
    KAKAOPAY_API_EXCEPTION("100","카카오페이 API 호출 실패"),
    ENTITY_NOT_FOUND_EXCEPTION("101","리소스가 없습니다.")
    ;

    private String code;
    private String desc;

    KakaopayApiCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
