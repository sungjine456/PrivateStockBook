package com.psb.stockbook.domains.books;

import org.hibernate.annotations.ColumnDefault;

import com.psb.stockbook.domains.stocks.StockEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "books")
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue
    private long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_idx", insertable = false, updatable = false)
    private StockEntity stock;

    @Column(name = "stock_idx")
    private long stockIdx;

    @Column()
    @ColumnDefault("0")
    private int count;

    @Column()
    @ColumnDefault("0")
    private int price;

    @Column(name = "sell_flag")
    private boolean sellFlag;

    public BookEntity(long stockIdx, int count, int price, boolean sellFlag) {
        this.stockIdx = stockIdx;
        this.count = count;
        this.price = price;
        this.sellFlag = sellFlag;
    }

    public BookEntity(BookDto dto) {
        this(dto.getStock().getIdx(), dto.getCount(), dto.getPrice(), dto.isSellFlag());
    }

    public BookDto getDto() {
        return new BookDto(stock.getDto(), count, price, sellFlag);
    }
}
