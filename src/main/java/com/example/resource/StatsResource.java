package com.example.resource;

import com.example.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yaroslav.frolikov
 */
@Path("stats")
@Singleton
public class StatsResource {
    private Logger logger = LoggerFactory.getLogger(StatsResource.class);

    @Inject
    StatsService statsService;

    @GET
    @Path("overallbalance")
    @Produces(MediaType.TEXT_PLAIN)
    public Long getOverallBalance() {
        try {
            return statsService.getOverallBalance();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
