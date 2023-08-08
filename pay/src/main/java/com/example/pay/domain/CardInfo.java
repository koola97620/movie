package com.example.pay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.ObjectUtils;

public record CardInfo(
        String purchase_corp,
        String purchase_corp_code,
        String issuer_corp,
        String issuer_corp_code,
        String kakaopay_puchase_corp,
        String kakaopay_puchase_corp_code,
        String kakaopay_issuer_corp,
        String kakaopay_issuer_corp_code,
        String bin,
        String card_type,
        String install_month,
        String approve_id,
        String card_mid,
        String interest_free_install,
        String card_item_code
) {
    @JsonIgnore
    public Integer getNumberInstallMonths() {
        if (ObjectUtils.isEmpty(install_month)) {
            return 0;
        }
        return Integer.parseInt(install_month);
    }
}
