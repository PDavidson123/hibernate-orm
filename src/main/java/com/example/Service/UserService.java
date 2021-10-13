package com.example.Service;

import com.example.Repository.UserRepository;
import com.example.data.Address;
import com.example.data.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager manager;

    @Inject
    UserRepository userRepository;

    public Response addUser(User user) {
        if (!userRepository.userExist(user)) {
            userRepository.addUser(user);
            return Response.ok(user).build();
        } else {
            return Response.status(400).entity("User exist.").build();
        }

    }

    public List<User> listUsers() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    public List<Address> getUserAddresses(int id) {
        return manager.createQuery("SELECT a FROM Address a WHERE a.user.userID = " + id, Address.class).getResultList();
    }

    public Address addAddress(int id, Address address) {
        User user = manager.createQuery("SELECT u FROM User u WHERE u.userID = " + id, User.class).getSingleResult();
        address.setUser(user);
        manager.merge(address);
        return address;
    }

}
