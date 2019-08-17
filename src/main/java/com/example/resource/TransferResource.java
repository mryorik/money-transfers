package com.example.resource;

import com.example.entity.Transfer;
import com.example.service.TransferService;

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
    @Inject
    TransferService transferService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer makeTransfer(Transfer transfer) {
        return transferService.makeTransfer(transfer);
    }
}
