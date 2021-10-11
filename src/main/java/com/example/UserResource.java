package com.example;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User saveUser(User user) {
        manager.persist(user);
        return user;
    }
}
