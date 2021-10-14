package com.example.Repository;

import com.example.data.Product;
import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Response addNewProductToUser(Product product) {
        persist(product);
        return Response.ok("Successfull.").build();
    }

    public boolean productExist(Product product) {
        return !list("name", product.getName()).isEmpty();
    }

    public List<Product> getUserProducts(User user) {
        return list("userID", user.getUserID());
    }

}
