package com.psb.stockbook.domains.books;

import com.psb.stockbook.domains.stocks.StockDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private StockDto stock;
    private int count;
    private int price;
    private boolean sellFlag;

    @Override
    public String toString() {
        return String.format("stockIdx: %d, count : %d, price : %d, sellFlag : %s", stock.getIdx(), count, price,
                sellFlag);
    }
}
