package com.example.Service;

import com.example.data.Address;
import com.example.data.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager manager;

    public User addUser(User user) {
        manager.persist(user);
        return user;
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
