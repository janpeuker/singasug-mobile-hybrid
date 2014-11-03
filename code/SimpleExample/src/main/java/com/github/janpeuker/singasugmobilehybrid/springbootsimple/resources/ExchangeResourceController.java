package com.github.janpeuker.singasugmobilehybrid.springbootsimple.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain.Exchange;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by janpeuker on 22/10/14.
 */
@RestController
@EnableAutoConfiguration
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
