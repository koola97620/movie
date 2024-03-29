package com.example.pay.infra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayReadyApiRequest {
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private Integer quantity;
    private Integer total_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;
    private String payment_method_type;
    private Integer install_month;
    private Integer tax_free_amount;
}
