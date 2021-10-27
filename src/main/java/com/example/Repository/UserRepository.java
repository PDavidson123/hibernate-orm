package com.example.Repository;

import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.runtime.graal.service.jacc.Delete_StandardJaccServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.lang.Integer.parseInt;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public boolean addUser(User user) {
        try {
            user.setPassword(String.valueOf(user.getPassword().hashCode()));
            persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userExist(User user) {
        try {
            return !list("name", user.getName()).isEmpty();
        } catch(NullPointerException e) {
            return false;
        }

    }

    public User findByUserName(String userName) {
        try {
            return list("name", userName).get(0);
        } catch(Exception e) {
            return null;
        }
    }

    public boolean canLogIn(User user) {
        try {
            User usr = list("name", user.getName()).get(0);
            if(parseInt(usr.getPassword()) == parseInt(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
    }

    public List<User> listAllUser() {
        return listAll();
    }

    public User getUserByID(Long id) {
        return findById(id);
    }

}
