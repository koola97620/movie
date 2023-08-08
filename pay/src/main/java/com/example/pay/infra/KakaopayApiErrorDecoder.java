package com.example.pay.infra;

import com.example.pay.exception.KakaopayApiCallException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

import static com.example.pay.exception.KakaopayPaymentExceptionMessage.NOT_CONVERT_ERROR_RESPONSE;

public class KakaopayApiErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public KakaopayApiErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        KakaopayApiErrorResponse errorResponse;
        try {
            errorResponse = objectMapper.readValue(response.body().asInputStream(), KakaopayApiErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(NOT_CONVERT_ERROR_RESPONSE);
        }
        return new KakaopayApiCallException(errorResponse);
    }
}
