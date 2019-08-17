package com.example.resource;

import com.example.entity.Account;
import com.example.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("account")
@Singleton
public class AccountResource {
    @Inject
    AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Account get(@PathParam("id") Long id) {
        return accountService.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Account create(Account account) {
        return accountService.save(account);
    }
}
