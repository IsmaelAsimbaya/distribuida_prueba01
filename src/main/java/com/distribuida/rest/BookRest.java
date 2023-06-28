package com.distribuida.rest;

import com.distribuida.db.Books;
import com.distribuida.repo.BooksRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path(value = "/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class BookRest {

    Logger logger = LoggerFactory.getLogger(BookRest.class);

    @Inject
    BooksRepository repo;

    //Buscar un libro por id
    @GET
    @Path(value = "/{id}")
    public Response findById(@PathParam("id") Long id) {
        logger.debug("Consultando persona con id={}", id);

        var per = repo.findById(id);

        if( per == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(per)
                .build();
    }

    //listar todos los libros
    @GET
    public List<Books> findAll() {
        logger.debug("Listando libros");

        return repo.findAll()
                .list();
    }

    //insertar
    @POST
    public Response create(Books obj) {
        repo.persist(obj);

        return Response.status(Response.Status.CREATED.getStatusCode(), "record created")
                .build();
    }

    //actualizar
    @PUT
    @Path(value = "/{id}")
    public Response update( @PathParam("id") Long id, Books obj) {
        Books per = repo.findById(id);

        per.setIsbn(obj.getIsbn());
        per.setTitle(obj.getTitle());
        per.setAuthor(obj.getAuthor());
        per.setPrice(obj.getPrice());

        repo.persistAndFlush(per);

        return Response.ok().build();
    }

    //eliminar
    @DELETE
    @Path(value = "/{id}")
    public void delete(@PathParam("id") Long id) {
        repo.deleteById(id);
    }


}
