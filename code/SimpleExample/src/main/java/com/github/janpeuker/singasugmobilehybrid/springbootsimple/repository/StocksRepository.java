package com.github.janpeuker.singasugmobilehybrid.springbootsimple.repository;

import com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain.Stock;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by janpeuker on 30/10/14.
 */
@Repository
public class StocksRepository {

    private final Map<String, Stock> stocks = new HashMap<String, Stock>();

    public StocksRepository() {
        stocks.put("AAPL", new Stock("AAPL", "Apple", "NASDAQ"));
        stocks.put("GOOG", new Stock("GOOG", "Google", "NASDAQ"));
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

}
