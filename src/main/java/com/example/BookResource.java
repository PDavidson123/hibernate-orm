package com.example;

import com.example.data.Book;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/book")
public class BookResource {

    @Inject
    EntityManager manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> hello() {

        return manager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book saveBook(Book book) {
        manager.persist(book);
        return book;
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book updateBook(@PathParam("id") Integer id, Book book) {
        manager.merge(book);
        return book;
    }
}