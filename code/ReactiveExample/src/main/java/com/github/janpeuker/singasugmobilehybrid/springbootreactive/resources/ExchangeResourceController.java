package com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources;

import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Exchange;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain.Stock;
import com.github.janpeuker.singasugmobilehybrid.springbootreactive.repository.StocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by janpeuker on 22/10/14.
 */
@RestController
@RequestMapping(value = "/exchanges", produces = "application/vnd.janpeuker.exchanges+json; version=1.0")
@ExposesResourceFor(Exchange.class)
public class ExchangeResourceController {

    private final static Map<String, Exchange> exchanges = new HashMap<String, Exchange>();

    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private EntityLinks entityLinks;

    Logger logger = LoggerFactory.getLogger(ExchangeResourceController.class);

    @PostConstruct
    public void init() {
        createNewExchange("NASDAQ", "New York, USA");
        createNewExchange("SGX", "Singapore, Singapore");
    }

    private void createNewExchange(String shorthand, String location) {
        Exchange e = new Exchange(shorthand, location);
        exchanges.put(e.getName(), e);
    }

    private Exchange getExchangeWithLink(String shorthand) {
        // This only works in a Request i.e. with a new Object, otherwise HATEOAS can't find a Request
        // which is needed because Identifyable cannot be overriden with an actual ID
        Exchange e = exchanges.get(shorthand);
        e = new Exchange(e.getName(), e.getLocation());
        for (Stock s : stocksRepository.getAllStocksForExchange(shorthand)) {
            e.add(entityLinks.linkToSingleResource(Stock.class, s.getSymbol()).withRel("stocks"));
        }
        return e;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Exchange>> showAllExchanges() {

        Collection<Exchange> returnCollection = new ArrayList<Exchange>();
        for (Exchange e : exchanges.values()) {
            Exchange f = new Exchange(e.getName(), e.getLocation());
            f.add(entityLinks.linkToSingleResource(Exchange.class, e.getName()).withSelfRel());
            returnCollection.add(f);
        }

        return new ResponseEntity<Collection<Exchange>>(returnCollection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{shorthand}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Exchange> showExchange(@PathVariable(value="shorthand") String shorthand) {

        ResponseEntity<Exchange> response = null;

        if (ExchangeResourceController.exchanges.containsKey(shorthand)) {

            Exchange exchange = getExchangeWithLink(shorthand);

            HttpHeaders headers = new HttpHeaders();
            headers.setPragma("cache");
            headers.setExpires(ZonedDateTime.now().plusDays(1).toInstant().toEpochMilli());

            response = new ResponseEntity<Exchange>(exchange, headers, HttpStatus.OK);

        } else throw new IllegalArgumentException();

        return response;

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound(HttpServletRequest request, Exception exception) {
        logger.info("{} caused {}", request.getRequestURL(), exception.getMessage());
    }

}
