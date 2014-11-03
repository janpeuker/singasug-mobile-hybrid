package com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Price;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Stock;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.repository.StocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Created by janpeuker on 22/10/14.
 */
@RestController
@ExposesResourceFor(Stock.class)
@RequestMapping(value = "/stocks", produces = "application/vnd.janpeuker.stock+json; version=1.0")
public class StockResourceController {

    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private EntityLinks entityLinks;

    Logger logger = LoggerFactory.getLogger(StockResourceController.class);

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Collection<Stock>> showAllStocks(@RequestParam(value = "exchange", required = false) String exchange) {

        Collection<Stock> stocks = null;

        if ((null != exchange)&&(!exchange.isEmpty())) {
            stocks = stocksRepository.getAllStocksForExchange(exchange);
        } else {
            stocks = stocksRepository.getAllStocks().values();
        }

        return new ResponseEntity<Collection<Stock>>(stocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Stock> showStock(@PathVariable(value="symbol") String symbol) {

        ResponseEntity<Stock> response = null;
        Stock stock = null;

        if (stocksRepository.getAllStocks().containsKey(symbol)) {

            // Need to create a "detached" Entitity here as Spring uses Link as Identity
            stock = getStockWithLink(symbol);

            HttpHeaders headers = new HttpHeaders();
            headers.setPragma("cache");
            headers.setCacheControl("public");
            headers.setExpires(ZonedDateTime.now().plusDays(1).toInstant().toEpochMilli());

            response = new ResponseEntity<Stock>(stock, headers, HttpStatus.OK);

        } else throw new IllegalArgumentException();


        return response;
    }

    private Stock getStockWithLink(String symbol) {
        Stock s = stocksRepository.getStock(symbol);
        Stock x = new Stock(s.getSymbol(), s.getName(), s.getExchange());
        x.add(entityLinks.linkToSingleResource(Price.class, symbol).withRel("price"));
        return x;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound(HttpServletRequest request, Exception exception) {
        logger.info("{} caused {}", request.getRequestURL(), exception.getMessage());
    }


}
