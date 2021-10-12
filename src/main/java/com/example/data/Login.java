package com.example.data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @Column(name = "dateTime")
    private Date dateTime;

    public Login() {}

    public Login(Integer loginID, User user, Date dateTime) {
        this.loginID = loginID;
        this.user = user;
        this.dateTime = dateTime;
    }

    public Integer getLoginID() {
        return loginID;
    }

    public void setLoginID(Integer loginID) {
        this.loginID = loginID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
