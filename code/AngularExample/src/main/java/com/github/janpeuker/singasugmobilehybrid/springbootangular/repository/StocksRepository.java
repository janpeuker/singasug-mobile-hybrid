package com.github.janpeuker.singasugmobilehybrid.springbootangular.repository;

import com.github.janpeuker.singasugmobilehybrid.springbootangular.domain.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by janpeuker on 30/10/14.
 */
@Repository
public class StocksRepository {

    private final Map<String, Stock> stocks = new HashMap<String, Stock>();

    Logger logger = LoggerFactory.getLogger(StocksRepository.class);

    public StocksRepository() {
        addNewStock("AAPL", "Apple", "NASDAQ");
        addNewStock("GOOG", "Google", "NASDAQ");
        addNewStock("BAC", "Bank of America", "NASDAQ");
        addNewStock("NTC", "Intel", "NASDAQ");
        addNewStock("FB", "Facebook", "NASDAQ");
        addNewStock("MSFT", "Microsoft", "NASDAQ");
        addNewStock("YHOO", "Yahoo", "NASDAQ");
        addNewStock("SBUX", "Starbucks", "NASDAQ");
        addNewStock("U11", "UOB", "SGX");
        addNewStock("D05", "DBS", "SGX");
        addNewStock("Z74", "Singtel", "SGX");
        addNewStock("O39", "OCBC Bank", "SGX");
        addNewStock("N21", "Noble", "SGX");
        addNewStock("MC0", "Global Logistics", "SGX");

        logger.info("{} Stocks in Repository", stocks.size());
    }

    private void addNewStock(final String symbol, final String commonName, final String exchange) {
        final Stock s = new Stock(symbol, commonName, exchange);
        stocks.put(s.getSymbol(), s);
    }

    public Map<String, Stock> getAllStocks() {
        return stocks;
    }

    public Collection<Stock> getAllStocksForExchange(final String exchange)
    {
        final Collection<Stock> results = new HashSet<Stock>();
        stocks.values().forEach((s) -> {
            if (s.getExchange().equalsIgnoreCase(exchange)) results.add(s);
        });
        return results;
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }
}
