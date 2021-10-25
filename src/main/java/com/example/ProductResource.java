package com.example;

import com.example.Service.ProductService;
import com.example.data.Product;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/product")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public List<Product> listAllProduct() {
        return productService.listAllProduct();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public Response addProduct(@Context SecurityContext ctx, Product product) {
        String name = ctx.getUserPrincipal().getName();
        return productService.addNewProductToUser(name, product);
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public Response editProduct(@Context SecurityContext ctx,@PathParam("id") Long id, Product product) {
        String name = ctx.getUserPrincipal().getName();
        return productService.editUserProduct(id, name, product);
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public Response deleteProduct(@Context SecurityContext ctx,@PathParam("id") Long id) {
        String name = ctx.getUserPrincipal().getName();
        return productService.deleteUserProduct(id, name);
    }

    /**** Filter methods ****/
    @Path("/filter/my_products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public List<Product> listUserProduct(@Context SecurityContext ctx) {
        String name = ctx.getUserPrincipal().getName();
        return productService.listUserProducts(name);
    }

    @Path("/filter") //http://localhost:8080/product/filter?filterOpt=(all/my/more/less)&price=5000
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public List<Product> filterProducts(@Context SecurityContext ctx, @QueryParam("filterOpt") String filterOpt, @QueryParam("price") Long price)  {
        String name = ctx.getUserPrincipal().getName();
        return productService.filterProducts(name, filterOpt, price);
    }
}
