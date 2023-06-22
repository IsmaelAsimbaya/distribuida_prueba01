package com.distribuida.rest;

import com.distribuida.repo.PersonasRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(value = "/personas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class PersonaRest {

    Logger logger = LoggerFactory.getLogger(PersonaRest.class);

    @Inject
    PersonasRepository repo;

    @GET
    @Path(value = "/{id}")
    public Response finByid(@PathParam("id") Long id){
        logger.debug("Consultando persona con id={}", id);

        var per = repo.findByIdOptionalCache(id);

        if (per.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(per.get())
                .build();
    }


}
