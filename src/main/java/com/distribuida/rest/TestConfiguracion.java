package com.distribuida.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestConfiguracion {

    @Inject
    @ConfigProperty(name = "prop.mensaje")
    String mensaje;

    @GET
    public String test(){
        Config config = ConfigProvider.getConfig();

        var sources = config.getConfigSources();
        for (var it:sources){
            System.out.printf("Source [%s]--> ordinal %d\n",
                    it.getName(),
                    it.getOrdinal());
        }
        //String username =
                //config.getValue("quarkus.datasource.username", String.class);
        //String mensaje =
                //config.getValue("prop.mensaje", String.class);

        return mensaje;
    }



}
