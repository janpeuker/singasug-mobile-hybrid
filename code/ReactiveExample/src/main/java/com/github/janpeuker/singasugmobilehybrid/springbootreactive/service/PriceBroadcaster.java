package com.github.janpeuker.singasugmobilehybrid.springbootreactive.service;

import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Stock;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.repository.StocksRepository;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources.PriceUpdatesController;
import org.atmosphere.cpr.MetaBroadcaster;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by janpeuker on 3/11/14.
 */
public class PriceBroadcaster  {

    private MetaBroadcaster broadcaster;

    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    public PriceBroadcaster(MetaBroadcaster broadcaster) {
        this.broadcaster = broadcaster;

        ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(10);

        for (Stock stock : stocksRepository.getAllStocks().values()) {
            scheduler.scheduleAtFixedRate(new BroadcastTask(stock.getSymbol()), 5, 1, TimeUnit.SECONDS);
        }
    }

    class BroadcastTask implements Runnable {

        private String symbol;

        public BroadcastTask(String symbol) {
            this.symbol = symbol;
        }

        public void run() {
            broadcaster.broadcastTo(PriceUpdatesController.PRICEUPDATE_URL + "/" + symbol, stockPriceService.getPriceForSymbol(symbol));
        }

    }

}
