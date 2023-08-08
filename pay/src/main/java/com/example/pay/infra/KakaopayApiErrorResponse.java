package com.example.pay.infra;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class KakaopayApiErrorResponse {
    private String code;
    private String msg;
    private Map<String, String> extras;
}
