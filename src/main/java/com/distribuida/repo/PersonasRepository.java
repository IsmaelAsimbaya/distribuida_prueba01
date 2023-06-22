package com.distribuida.repo;

import com.distribuida.db.Persona;
import io.lettuce.core.RedisClient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class PersonasRepository implements PanacheRepositoryBase<Persona, Long> {

    @Inject
    RedisClient redisClient;

    public Optional<Persona> findByIdOptionalCache(Long id) {
        /**
         * Buscamos en el cache
         * esta en el cache?
         *      retornamos la instancia
         * si no esta ?
         *      buscar en la DB
         *      poner en el cache
         *      retornamos la instancia
         */

        try ( var conn = redisClient.connect() ) {

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return this.findByIdOptional(id);
    }


}
