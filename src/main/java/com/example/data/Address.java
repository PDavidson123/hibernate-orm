package com.example.data;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    private String city;
    private String roadName;
    private Integer houseNumber;


    public Address() {

    }

    public Address(Integer addressID, Integer userID, String city, String roadName, Integer houseNumber) {
        this.addressID = addressID;
        //this.userID = userID;
        this.city = city;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
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
