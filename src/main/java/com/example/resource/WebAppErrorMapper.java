package com.example.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author yaroslav.frolikov
 */
@Provider
public class WebAppErrorMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(javax.ws.rs.WebApplicationException exception) {
        return Response
                .status(exception.getResponse().getStatus())
                .entity(exception.getMessage())
                .build();
    }
}
