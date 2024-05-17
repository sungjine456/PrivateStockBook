package com.psb.stockbook.domains.stocks;

public enum StockKind {

    ETF("0"),
    채권("1"),
    주식("2"),
    금("3"),
    달러("4");

    private String id;

    private StockKind(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
