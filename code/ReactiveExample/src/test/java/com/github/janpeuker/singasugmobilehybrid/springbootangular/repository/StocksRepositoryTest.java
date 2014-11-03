package com.github.janpeuker.singasugmobilehybrid.springbootangular.repository;

import com.github.janpeuker.singasugmobilehybrid.springbootangular.Application;
import com.github.janpeuker.singasugmobilehybrid.springbootangular.service.StockPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StocksRepositoryTest {

    @Autowired
    private StocksRepository stocksRepository;

    @Test
    public void testGetAllStocksForExchange() throws Exception {
        assertTrue(stocksRepository.getAllStocksForExchange("NASDAQ").size() > 0);
    }

    @Test
    public void testGetStock() throws Exception {
        assertEquals("GOOG", stocksRepository.getStock("GOOG").getSymbol());
    }
}