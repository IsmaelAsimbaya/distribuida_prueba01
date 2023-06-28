package com.distribuida.repo;

import com.distribuida.db.Books;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BooksRepository implements PanacheRepositoryBase<Books, Long> {

}
