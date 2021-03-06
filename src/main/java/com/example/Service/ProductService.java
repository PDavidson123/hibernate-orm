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

    public Response addNewProductToUser(String name, Product product) {
        if (product.getName() == null || product.getName().equals("")) {
            return Response.status(400).entity("Product don't have name.").build();
        } else if (userRepository.findByUserName(name) != null) {
            User user = userRepository.findByUserName(name);
            List<Product> userProducts = productRepository.getUserProducts(user);
            for (Product prod : userProducts) {
                if (prod.getName().equals(product.getName())) {
                    return Response.status(400).entity("Product exist with this userID.").build();
                }
            }
            product.setUser(user);
            return productRepository.addNewProductToUser(product);
        } else {
            return Response.status(400).entity("Wrong userID.").build();
        }
    }

    public List<Product> listUserProducts(String name) {
        if (userRepository.findByUserName(name) != null) {
            return productRepository.getUserProducts(userRepository.findByUserName(name));
        } else {
            return Collections.<Product>emptyList();
        }
    }

    public Response editUserProduct(Long id, String name, Product product) {
        if("".equals(product.getName()) || product.getDescription() == null || product.getPrice() == null) {
            return Response.status(400).entity("Empty tags.").build();
        } else if (userRepository.findByUserName(name) != null) {
            if(productRepository.getProductOwnerByID(id).equals(name)) {
                product.setUser(userRepository.findByUserName(name));
                if(productRepository.updateProduct(id, product)) {
                    return Response.ok().entity("Product updated successfully.").build();
                } else {
                    return Response.status(400).entity("Can't update product.").build();
                }
            } else {
                return Response.status(400).entity("No permission.").build();
            }
        } else {
            return Response.status(400).entity("User does not exist.").build();
        }
    }

    public Response deleteUserProduct(Long id, String name) {
        if (userRepository.findByUserName(name) != null) {
            if(productRepository.getProductOwnerByID(id).equals(name)) {
                if(productRepository.deleteProduct(id)) {
                    return Response.ok().entity("Product deleted successfully.").build();
                } else {
                    return Response.status(400).entity("Can't delete product.").build();
                }
            } else {
                return Response.status(400).entity("No permission.").build();
            }
        } else {
            return Response.status(400).entity("User does not exist.").build();
        }
    }

    public List<Product> listAllProduct() {
        return productRepository.listAllProduct();
    }

    /**** Filter ****/

    public List<Product> filterProducts(String name, String filterOpt, Long price) {
        switch(filterOpt)
        {
            case "all":
                return listAllProduct();
            case "my":
                return listUserProducts(name);
            case "more":
                return productRepository.getProductsByPrice(price, true);
            case "less":
                return productRepository.getProductsByPrice(price, false);
            default:
                return null;
        }
    }
}
