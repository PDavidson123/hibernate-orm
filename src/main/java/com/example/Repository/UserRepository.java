package com.example.Repository;

import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.runtime.graal.service.jacc.Delete_StandardJaccServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User addUser(User user) {
        persist(user);
        return user;
    }

    public boolean userExist(User user) {
        return !list("name", user.getName()).isEmpty();
    }

    public List<User> listAllUser() {
        return listAll();
    }

}
