package com.github.janpeuker.singasugmobilehybrid.springbootangular.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootangular.domain.Exchange;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.ExposesResourceFor;
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
@ExposesResourceFor(Exchange.class)
public class ExchangeResourceController {

    private final Map<String, Exchange> exchanges = new HashMap<String, Exchange>();

    public ExchangeResourceController() {
        exchanges.put("NASDAQ", new Exchange("NASDAQ", "New York, USA"));
        exchanges.put("SGX", new Exchange("SGX", "Singapore, Singapore"));
    }

    @RequestMapping(value = "/exchanges", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Collection<Exchange>> getExchangeResource() {

        Collection<Exchange> exchanges = this.exchanges.values();

        return new ResponseEntity<Collection<Exchange>>(exchanges, HttpStatus.OK);
    }


}
