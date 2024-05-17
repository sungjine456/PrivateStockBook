package com.psb.stockbook.domains.stocks;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class StockEntity {

    @Id
    @GeneratedValue
    private long idx;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String code;

    @Column(name = "stock_kind")
    @Convert(converter = StockKindConverter.class)
    private StockKind stockKind;

    public StockEntity(String name, String code, StockKind stockKind) {
        this.name = name;
        this.code = code;
        this.stockKind = stockKind;
    }

    public StockEntity(StockDto dto) {
        this(dto.getName(), dto.getCode(), dto.getStockKind());
    }

    public StockDto getDto() {
        return new StockDto(name, code, stockKind);
    }
}
