package com.example.movie.pay.app;

import com.example.movie.pay.domain.Amount;
import com.example.movie.pay.domain.CardInfo;
import com.example.movie.pay.domain.PaymentMethodType;

import java.time.LocalDateTime;

public record PaymentResultResponse(
        String aid,
        String tid,
        String cid,
        String sid,
        String partner_order_id,
        String partner_user_id,
        PaymentMethodType payment_method_type,
        Amount amount,
        CardInfo card_info,
        String item_name,
        String item_code,
        Integer quantity,
        LocalDateTime created_at,
        LocalDateTime approved_at
) {
}
