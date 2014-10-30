package com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain;

/**
 * Created by janpeuker on 30/10/14.
 */
public class Price {

    private String symbol;
    private long price;
    private long asOfTime;
    private String currency;

    public Price(String symbol, long price, long dateAsOf, String currency) {
        this.symbol = symbol;
        this.price = price;
        this.asOfTime = dateAsOf;
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAsOfTime() {
        return asOfTime;
    }

    public void setAsOfTime(long asOfTime) {
        this.asOfTime = asOfTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
