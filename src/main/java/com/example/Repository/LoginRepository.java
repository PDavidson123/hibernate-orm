package com.example.Repository;

import com.example.data.Login;
import com.example.data.User;
import com.example.security.jwt.GenerateToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<Login> {

    public void addLoginDateToUser(User user) {
        Date date = java.util.Calendar.getInstance().getTime();

        Login login = new Login(user,date);
        login.setUser(user);
        System.out.println("New login: " + login.getDateTime());

        persist(login);
    }

    public boolean isUserLoggedIn(Long userID) {
        List<Login> userLogins = list("userID", userID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, (int) -GenerateToken.expTime);

        for(Login userLogin : userLogins) {
            if(userLogin.getDateTime().after(cal.getTime())) {
                return true;
            }
        }

        return false;
    }

}
