package com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain;

/**
 * Created by janpeuker on 22/10/14.
 */
public class Stock {

    private String symbol;
    private String name;
    private String exchange;

    public Stock(String symbol, String name, String exchange) {
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
    }

    public String getSymbol() {

        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
