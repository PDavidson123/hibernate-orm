package com.example;

import com.example.Service.ProductService;
import com.example.data.Product;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Product> listAllProduct() {
        return productService.listAllProduct();
    }

    /* Az ID a userID, akihez tartozik a product.*/
    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addNewProduct(@PathParam("id") Long id, Product product) {
        return productService.addNewProductToUser(id, product);
    }

    /* Az ID a userID, akinek a productjait kérjük le.*/
    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Product> addNewProduct(@PathParam("id") Long id) {
        return productService.getUserProducts(id);
    }
}
