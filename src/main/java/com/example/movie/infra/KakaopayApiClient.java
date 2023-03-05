package com.example.movie.infra;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers(value = {"Authorization: KakaoAK {Authorization}", "Content-Type: application/x-www-form-urlencoded"})
public interface KakaopayApiClient {

    @RequestLine("POST /v1/payment/ready")
    KakaopayReadyApiResponse ready(@Param("Authorization") String adminKey, KakaopayReadyApiRequest request);
}
