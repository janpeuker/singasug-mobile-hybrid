package com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by janpeuker on 30/10/14.
 */
public class Price extends ResourceSupport {

    private String symbol;
    private BigDecimal price;
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime asOfTime;
    private String currency;

    public Price(String symbol, BigDecimal price, ZonedDateTime dateAsOf, String currency) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getAsOfTime() {
        return asOfTime;
    }

    public void setAsOfTime(ZonedDateTime asOfTime) {
        this.asOfTime = asOfTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
