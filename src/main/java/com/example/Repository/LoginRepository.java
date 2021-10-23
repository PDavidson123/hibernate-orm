package com.example.Repository;

import com.example.data.Login;
import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<Login> {

    public void addLoginDateToUser(User user) {
        Date date = java.util.Calendar.getInstance().getTime();

        Login login = new Login(user,date);
        login.setUser(user);
        System.out.println("New login: " + login.getDateTime());

        persist(login);
    }

}
