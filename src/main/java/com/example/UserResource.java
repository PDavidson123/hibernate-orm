package com.example;

import com.example.Service.AddressService;
import com.example.Service.TokenHashService;
import com.example.Service.UserService;
import com.example.data.Address;
import com.example.data.User;
import com.example.security.jwt.GenerateToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/user")
@Table(name = "User")
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    AddressService addressService;
    @Inject
    TokenHashService tokenHashService;

    @Path("/register")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Transactional
    public String saveUser(User user) {
        userService.addUser(user);
        return userService.checkLoginAndGetToken(user);
    }

    @Path("/register_addresses")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public Response addAddresses(@Context SecurityContext ctx, List<Address> addresses) {
        if (!tokenHashService.checkTokenLogout(ctx)) {
            String name = ctx.getUserPrincipal().getName();
            for (Address address : addresses) {
                addressService.addNewAddressWithUserName(name, address);
            }
            return Response.ok("All addresses successfully added.").build();
        } else {
            return Response.status(400).entity("Wrong token.").build();
        }
    }

    @Path("/login")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    @Transactional
    public String loginAndGetToken(User user) {
        return userService.checkLoginAndGetToken(user);
    }

    @Path("/logout")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    @Transactional
    public Response logOut(@Context SecurityContext ctx) {
        return userService.logOut(ctx);
    }

}
