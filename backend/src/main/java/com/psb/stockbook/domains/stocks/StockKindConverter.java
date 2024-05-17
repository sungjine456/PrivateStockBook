package com.psb.stockbook.domains.stocks;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StockKindConverter implements AttributeConverter<StockKind, String> {

    @Override
    public String convertToDatabaseColumn(StockKind fruit) {
        if (fruit == null) {
            return null;
        }
        return fruit.getId();
    }

    @Override
    public StockKind convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(StockKind.values())
                .filter(f -> f.getId().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}