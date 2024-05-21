package com.psb.stockbook.domains.stocks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private long idx;
    private String name;
    private String code;
    private StockKind stockKind;

    @Override
    public String toString() {
        return String.format("idx: %d, name : %s, code : %s, stockKind : %s", idx, name, code, stockKind);
    }
}
