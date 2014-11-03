package com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Price;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

/**
 * Created by janpeuker on 25/10/14.
 */
@RestController
@ExposesResourceFor(Price.class)
@RequestMapping(value = "/price", produces="application/vnd.janpeuker.price+json; version=1.0")
public class StockPriceResourceController {

    @Autowired
    private StockPriceService stockPriceService;

    @RequestMapping(value = "{stock}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Price> getPriceForStock(@PathVariable(value="stock") String stock) {

        Price price = stockPriceService.getPriceForSymbol(stock);

        HttpHeaders headers = new HttpHeaders();
        headers.setPragma("cache");
        headers.setCacheControl("public");
        headers.setExpires(ZonedDateTime.now().plusSeconds(5).toInstant().toEpochMilli());

        ResponseEntity response =  new ResponseEntity<Price>(price, headers, HttpStatus.OK);

        return response;
    }



}
