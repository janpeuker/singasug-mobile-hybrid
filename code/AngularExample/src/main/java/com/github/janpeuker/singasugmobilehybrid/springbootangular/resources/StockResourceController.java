package com.github.janpeuker.singasugmobilehybrid.springbootangular.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootangular.domain.Stock;
import com.github.janpeuker.singasugmobilehybrid.springbootangular.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by janpeuker on 22/10/14.
 */
@RestController
@EnableAutoConfiguration
@ExposesResourceFor(Stock.class)
@RequestMapping("/stocks")
public class StockResourceController {

    @Autowired
    private StocksRepository stocksRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Collection<Stock>> showAllStocks(@RequestParam(value = "exchange", required = false) String exchange) {

        Collection<Stock> stocks = stocksRepository.getStocks().values();

        return new ResponseEntity<Collection<Stock>>(stocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Stock> showStock(@PathVariable(value="symbol") String symbol) {

        ResponseEntity<Stock> response = null;

        if (stocksRepository.getStocks().containsKey(symbol)) {

            Stock stock = stocksRepository.getStocks().get(symbol);
            // stock.(.withSelfRel());

            response = new ResponseEntity<Stock>(stock, HttpStatus.OK);
        } else throw new IllegalArgumentException();

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound() {

    }


}
