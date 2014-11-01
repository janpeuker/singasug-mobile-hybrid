package com.github.janpeuker.singasugmobilehybrid.springbootsimple.service;

import com.github.janpeuker.singasugmobilehybrid.springbootsimple.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StockPriceServiceTest {

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void testPriceIsRandomForInvalidValues() throws Exception {
        assertTrue(stockPriceService.getPriceForSymbol("").doubleValue() >= 0);
        assertTrue(stockPriceService.getPriceForSymbol(null).doubleValue() >= 0);
    }

    @Test
    public void testPriceIsHigherThanZero() throws Exception {
        assertTrue(stockPriceService.getPriceForSymbol("GOOG").doubleValue() >= 0);
    }

    @Test
    public void testTwoPricesAreNeverTheSame() throws Exception {
        assertTrue(stockPriceService.getPriceForSymbol("GOOG").doubleValue() != stockPriceService.getPriceForSymbol("GOOG").doubleValue());
        // This Tests could actually fail as there is a very low probability that the values are the same
        assertTrue(stockPriceService.getPriceForSymbol("AAPL").doubleValue() != stockPriceService.getPriceForSymbol("GOOG").doubleValue());
    }
}