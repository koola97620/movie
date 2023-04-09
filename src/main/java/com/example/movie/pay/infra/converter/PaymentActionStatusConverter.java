package com.example.movie.pay.infra.converter;


import com.example.movie.pay.domain.PaymentActionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaymentActionStatusConverter implements AttributeConverter<PaymentActionStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PaymentActionStatus type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    public PaymentActionStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return PaymentActionStatus.ofCode(code);
    }
}
