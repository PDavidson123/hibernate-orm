package com.example.Service;

import com.example.Repository.ProductRepository;
import com.example.Repository.UserRepository;
import com.example.data.Address;
import com.example.data.Product;
import com.example.data.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;
    @Inject
    UserRepository userRepository;

    public Response addNewProductToUser(Long id, Product product) {
        if(userRepository.userExist(userRepository.getUserByID(id))) {
            List<Product> userProducts= productRepository.getUserProducts(userRepository.getUserByID(id));
            for(Product prod : userProducts) {
                if(prod.getName().equals(product.getName())) {
                    return Response.status(400).entity("Product exist with this userID.").build();
                }
            }
            product.setUser(userRepository.getUserByID(id));
            return productRepository.addNewProductToUser(product);
        } else {
            return Response.status(400).entity("Wrong userID.").build();
        }
    }

    public List<Product> getUserProducts(Long id) {
        User user = userRepository.getUserByID(id);
        if(userRepository.userExist(user)) {
            return productRepository.getUserProducts(userRepository.getUserByID(id));
        } else {
            return Collections.<Product>emptyList();
        }
    }

    public List<Product> listAllProduct() {
        return productRepository.listAllProduct();
    }

}
