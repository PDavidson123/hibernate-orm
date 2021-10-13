package com.example.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String name;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    public User() {

    }

    public User(Long userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
