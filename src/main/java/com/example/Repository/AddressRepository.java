package com.example.Repository;

import com.example.data.Address;
import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public List<Address> getUserAddresses(User user) {
        try {
            return list("userID", user.getUserID());
        } catch (NullPointerException e) {
            return Collections.<Address>emptyList();
        }
    }

    public Response addNewAddress(Address address) {
        try {
            persist(address);
            return Response.ok().entity("Address added successfully.").build();
        } catch(Exception e) {
            return Response.status(400).entity(e).build();
        }

    }
}
