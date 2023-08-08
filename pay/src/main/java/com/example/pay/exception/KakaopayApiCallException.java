package com.example.pay.exception;

import com.example.pay.infra.KakaopayApiErrorResponse;

public class KakaopayApiCallException extends RuntimeException {

    private final KakaopayApiErrorResponse errorResponse;
    private final String errorMsg;

    public KakaopayApiCallException(KakaopayApiErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
        this.errorMsg = KakaopayErrorCodeMap.getErrorMsg(errorResponse.getCode());
    }

    public KakaopayApiErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
