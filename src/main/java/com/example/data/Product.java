package com.example.data;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    private String name;
    private String description;
    private Integer price;

    public Product() {}

    public Product(Long productID, User user, String name, String description, Integer price) {
        this.productID = productID;
        this.user = user;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, Integer price, Long productID) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
