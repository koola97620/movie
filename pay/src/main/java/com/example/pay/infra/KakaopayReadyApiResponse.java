package com.example.pay.infra;

import java.time.LocalDateTime;

public record KakaopayReadyApiResponse(
        String tid,
        String next_redirect_app_url,
        String next_redirect_mobile_url,
        String next_redirect_pc_url,
        String android_app_scheme,
        String ios_app_scheme,
        LocalDateTime created_at
) {
}
