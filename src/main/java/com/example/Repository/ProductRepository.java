package com.example.Repository;

import com.example.data.Product;
import com.example.data.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Response addNewProductToUser(Product product) {
        try {
            persist(product);
            return Response.ok("Product added successfully.").build();
        } catch (Exception e) {
            return Response.status(400).entity("Can't add the item.").build();
        }
    }

    public boolean updateProduct(Long id, Product product) {
        try {
            update("name = '" + product.getName() + "' where productID = ?1", id);
            update("description = '" + product.getDescription() + "' where productID = ?1", id);
            update("price = '" + product.getPrice() + "' where productID = ?1", id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProduct(Long id) {
        try {
            delete(findById(id));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductOwnerByID(Long id) {
        return findById(id).getUser().getName();
    }

    public boolean productExist(Product product) {
        return !list("name", product.getName()).isEmpty();
    }

    public List<Product> getUserProducts(User user) {
        return list("userID", user.getUserID());
    }

    public List<Product> listAllProduct() {
        return listAll();
    }

    //true = more, false = less
    public List<Product> getProductsByPrice(Long price, boolean priceLine) {
        if(priceLine) {
            return list("price > " + price);
        } else {
            return list("price < " + price);
        }

    }
}
