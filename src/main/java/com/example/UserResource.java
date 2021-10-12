package com.example;

import com.example.data.Address;
import com.example.data.Book;
import com.example.data.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Table(name = "User")
public class UserResource {

    @Inject
    EntityManager manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listUsers() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Address> updateUser(@PathParam("id") int id) {
        return manager.createQuery("SELECT a FROM Address a WHERE a.user.userID = " + id, Address.class).getResultList();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Address addAddress(@PathParam("id") Integer id, Address address) {
        User user = manager.createQuery("SELECT u FROM User u WHERE u.userID = " + id, User.class).getSingleResult();
        address.setUser(user);
        manager.merge(address);
        return address;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User saveUser(User user) {
        manager.persist(user);
        return user;
    }
}
