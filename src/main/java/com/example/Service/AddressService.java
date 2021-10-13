package com.example.Service;

import com.example.Repository.AddressRepository;
import com.example.Repository.UserRepository;
import com.example.data.Address;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AddressService {

    @Inject
    AddressRepository addressRepository;
    @Inject
    UserRepository userRepository;

    public Response addNewAddress(Long userID, Address address) {
        if(userRepository.findById(userID) == null) {
            return Response.status(400).entity("Wrong user ID.").build();
        } else {
            address.setUser(userRepository.findById(userID));
            return addressRepository.addNewAddress(address);
        }
    }
}
