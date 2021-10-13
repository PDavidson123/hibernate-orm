package com.example.data;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    private String city;
    private String roadName;
    private Integer houseNumber;


    public Address() {

    }

    public Address(Long addressID,User user, String city, String roadName, Integer houseNumber) {
        this.addressID = addressID;
        this.user = user;
        this.city = city;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
    }

    public Address(Long addressID, String city, String roadName, Integer houseNumber) {
        this.addressID = addressID;
        this.city = city;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
