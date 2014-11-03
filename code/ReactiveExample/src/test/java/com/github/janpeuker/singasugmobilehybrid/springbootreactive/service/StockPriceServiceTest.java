package com.github.janpeuker.singasugmobilehybrid.springbootreactive.service;

import com.github.janpeuker.singasugmobilehybrid.springbootreactive.Application;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.service.StockPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StockPriceServiceTest {

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void testPriceIsRandomForInvalidValues() throws Exception {
        assertTrue(stockPriceService.getRawPriceForSymbol("").doubleValue() >= 0);
        assertTrue(stockPriceService.getRawPriceForSymbol(null).doubleValue() >= 0);
    }

    @Test
    public void testPriceIsHigherThanZero() throws Exception {
        assertTrue(stockPriceService.getRawPriceForSymbol("GOOG").doubleValue() >= 0);
    }

    @Test
    public void testTwoPricesAreNeverTheSame() throws Exception {
        assertTrue(stockPriceService.getRawPriceForSymbol("GOOG").doubleValue() != stockPriceService.getRawPriceForSymbol("GOOG").doubleValue());
        // This Tests could actually fail as there is a very low probability that the values are the same
        assertTrue(stockPriceService.getRawPriceForSymbol("AAPL").doubleValue() != stockPriceService.getRawPriceForSymbol("GOOG").doubleValue());
    }
}