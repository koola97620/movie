package com.example.movie.infra;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

@Headers(value = {"Authorization: KakaoAK {Authorization}", "Accept: application/json", "Content-Type: application/x-www-form-urlencoded;charset=utf-8"})
public interface KakaopayApiClient {

    @RequestLine("POST /v1/payment/ready")
    KakaopayReadyApiResponse ready(@Param("Authorization") String adminKey, KakaopayReadyApiRequest request);
}
