package com.example.resource;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * @author yaroslav.frolikov
 */
@Provider
public class NonWebAppErrorMapper implements ExtendedExceptionMapper<Exception> {
    @Override
    public boolean isMappable(Exception exception) {
        return !(exception instanceof WebApplicationException);
    }

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(INTERNAL_SERVER_ERROR).entity(exception.toString()).build();
    }
}
