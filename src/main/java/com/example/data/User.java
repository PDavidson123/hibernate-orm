package com.example.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name="ID_generator", sequenceName = "User", allocationSize=Integer.MAX_VALUE)
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_generator")
    private Integer userID;
    private String name;
    private String password;

    public User() {

    }

    public User(Integer userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
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
