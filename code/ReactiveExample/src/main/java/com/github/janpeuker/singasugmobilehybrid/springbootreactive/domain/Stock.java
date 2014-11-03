package com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain;

import org.springframework.hateoas.ResourceSupport;


/**
 * Created by janpeuker on 22/10/14.
 */
public class Stock extends ResourceSupport {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Stock stock = (Stock) o;

        if (exchange != null ? !exchange.equals(stock.exchange) : stock.exchange != null) return false;
        if (symbol != null ? !symbol.equals(stock.symbol) : stock.symbol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
        return result;
    }
}
