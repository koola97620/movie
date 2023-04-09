package com.example.movie.pay.infra.converter;



import com.example.movie.pay.domain.PgType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PgTypeConverter implements AttributeConverter<PgType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PgType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    public PgType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return PgType.ofCode(code);
    }
}
