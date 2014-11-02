package com.github.janpeuker.singasugmobilehybrid.springbootangular.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootangular.domain.Price;
import com.github.janpeuker.singasugmobilehybrid.springbootangular.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by janpeuker on 25/10/14.
 */
@RestController
@EnableAutoConfiguration
@ExposesResourceFor(Price.class)
public class StockPriceResourceController {

    @Autowired
    private StockPriceService stockPriceService;

    @RequestMapping(value = "/price/{stock}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Price> getPriceForStock(@PathVariable(value="stock") String stock) {

        Price price = new Price(stock, stockPriceService.getPriceForSymbol(stock), (new Date()).getTime(), "USD");

        return new ResponseEntity<Price>(price, HttpStatus.OK);
    }

}
