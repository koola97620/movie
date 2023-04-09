package com.example.movie.pay.infra.converter;

import com.example.movie.pay.domain.KakaopayPaymentStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class KakaopayPaymentStatusConverter implements AttributeConverter<KakaopayPaymentStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(KakaopayPaymentStatus status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public KakaopayPaymentStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return KakaopayPaymentStatus.ofCode(code);
    }
}
