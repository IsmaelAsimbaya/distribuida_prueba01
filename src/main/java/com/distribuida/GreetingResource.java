package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

class Message {
    private String msg = "Hola Mundo";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    //levantar okteto app
    //okteto build -t okteto.dev/app-personas:1.0.0
    @GET
    public List<Persona> getAllPersonas() {
        return Persona.listAll();
    }

    @POST
    @Transactional
    public Response createPersona(Persona persona) {
        Persona.persist(persona);
        return Response.ok(persona).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePersona(@PathParam("id") Long id, Persona personaData) {
        Persona persona = Persona.findById(id);
        if (persona == null) {
            throw new NotFoundException("Persona con ID " + id + " no encontrada");
        }
        persona.setName(personaData.getName());
        persona.persist();
        return Response.ok(persona).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePersona(@PathParam("id") Long id) {
        Persona persona = Persona.findById(id);
        if (persona == null) {
            throw new NotFoundException("Persona con ID " + id + " no encontrada");
        }
        persona.delete();
        return Response.noContent().build();
    }

}
