package com.github.janpeuker.singasugmobilehybrid.springbootsimple.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain.Stock;
import com.github.janpeuker.singasugmobilehybrid.springbootsimple.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by janpeuker on 22/10/14.
 */
@RestController
@EnableAutoConfiguration
public class StockResourceController {

    @Autowired
    private StocksRepository stocksRepository;

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Collection<Stock>> getStocksResource(@RequestParam(value = "exchange", required = false) String exchange) {

        Collection<Stock> stocks = stocksRepository.getStocks().values();

        return new ResponseEntity<Collection<Stock>>(stocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Stock> getStockResource(@PathVariable(value="symbol") String symbol) {

        ResponseEntity<Stock> response = null;

        if (stocksRepository.getStocks().containsKey(symbol)) {
            response = new ResponseEntity<Stock>(stocksRepository.getStocks().get(symbol), HttpStatus.OK);
        } else throw new IllegalArgumentException();

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound() {

    }


}
