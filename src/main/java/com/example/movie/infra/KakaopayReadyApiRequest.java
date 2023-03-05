package com.example.movie.infra;

import lombok.Builder;

public record KakaopayReadyApiRequest(
        String cid,
        String partner_order_id,
        String partner_user_id,
        String item_name,
        Integer quantity,
        Integer total_amount,
        String approval_url,
        String cancel_url,
        String fail_url,
        String payment_method_type,
        Integer install_month
) {
    @Builder
    public KakaopayReadyApiRequest {}
}
