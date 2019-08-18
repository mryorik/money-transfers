package com.example.resource;

import com.example.entity.Transfer;
import com.example.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yaroslav.frolikov
 */
@Path("transfer")
public class TransferResource {
    private Logger logger = LoggerFactory.getLogger(TransferResource.class);

    @Inject
    TransferService transferService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer makeTransfer(Transfer transfer) {
        try {
            return transferService.makeTransfer(transfer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
