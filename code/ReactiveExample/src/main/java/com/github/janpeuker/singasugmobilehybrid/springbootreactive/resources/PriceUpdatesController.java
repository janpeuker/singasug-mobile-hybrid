package com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by janpeuker on 3/11/14.
 */
@Service
@ManagedService(path = PriceUpdatesController.PRICEUPDATE_URL)
public class PriceUpdatesController {

    public static final String PRICEUPDATE_URL = "/priceupdates";

    Logger logger = LoggerFactory.getLogger(PriceUpdatesController.class);

    @Get
    public void init(AtmosphereResource resource) {
        resource.getResponse().setCharacterEncoding("UTF-8");
    }

    @Ready
    public void onReady(final AtmosphereResource resource) {
        logger.info("Opened: {} ", resource.uuid());
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        if (event.isCancelled()) {
            logger.info("Cancelled: {} ", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.info("Closed: {}", event.getResource().uuid());
        }
    }

}
