package com.example.resource;

import com.example.entity.AccountEntity;
import com.example.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("account")
@Singleton
public class AccountResource {
    @Inject
    AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public AccountEntity get(@PathParam("id") Long id) {
        return accountService.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public AccountEntity create(AccountEntity account) {
        return accountService.save(account);
    }
}
