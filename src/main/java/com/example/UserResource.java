package com.example;

import com.example.Service.AddressService;
import com.example.Service.UserService;
import com.example.data.Address;
import com.example.data.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Table(name = "User")
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    AddressService addressService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Address> getUserAddresses(@PathParam("id") Long id) {
        return userService.getUserAddresses(id);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addAddress(@PathParam("id") Long id, Address address) {
        return addressService.addNewAddress(id, address);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveUser(User user) {
        return userService.addUser(user);
    }
}
