package com.github.janpeuker.singasugmobilehybrid.springbootsimple.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by janpeuker on 30/10/14.
 */
@Service
public class StockPriceService {

    private final Map<String, BigDecimal> lastPrices = new ConcurrentHashMap<String, BigDecimal>();
    private enum Market { BULL, BEAR };
    private final Map<String, Market> priceTendencies = new ConcurrentHashMap<String, Market>();

    // TODO: Replace by JSR 354 in Java 9
    public BigDecimal getPriceForSymbol(final String symbol) {

        BigDecimal returnValue = null;

        // Not Thread Safe
        if ((null == symbol)||(symbol.isEmpty())||(!lastPrices.containsKey(symbol))) {
            returnValue = new BigDecimal(Math.round(Math.random() * 10000d + 1)/100d);
        } else {
            BigDecimal variance = new BigDecimal(Math.random());

            if (priceTendencies.get(symbol) == Market.BULL) {
                returnValue = lastPrices.get(symbol).add(variance).abs();
            } else {
                returnValue = lastPrices.get(symbol).subtract(variance).abs();
            }
        }

        returnValue = returnValue.setScale(3, BigDecimal.ROUND_UP);
        if ((null != symbol)&&(!symbol.isEmpty())) {
            lastPrices.put(symbol, returnValue);
            updateMarketTendency(symbol);
        }

        return returnValue;
    }

    private void updateMarketTendency(final String symbol) {
        if (!priceTendencies.containsKey(symbol)) priceTendencies.put(symbol, Market.BEAR);
        else {
            if (marketTurned()) {
                if (priceTendencies.get(symbol) == Market.BULL) {
                    priceTendencies.put(symbol, Market.BEAR);
                } else {
                    priceTendencies.put(symbol, Market.BULL);
                }
            }
        }
    }

    private boolean marketTurned() {
        return (Math.round(Math.random() * 10) == 10);
    }

}
