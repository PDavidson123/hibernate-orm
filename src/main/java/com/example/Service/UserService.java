package com.example.Service;

import com.example.Repository.AddressRepository;
import com.example.Repository.LoginRepository;
import com.example.Repository.TokenHashRepository;
import com.example.Repository.UserRepository;
import com.example.data.Address;
import com.example.data.User;
import com.example.security.jwt.GenerateToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    AddressRepository addressRepository;
    @Inject
    LoginRepository loginRepository;
    @Inject
    TokenHashService tokenHashService;

    public Response addUser(User user) {
        if(user.getName() == null || user.getName().equals("")) {
            return Response.status(400).entity("Empty name.").build();
        } else if(user.getPassword() == null || user.getPassword().equals("")) {
            return Response.status(400).entity("Empty password.").build();
        } else if (!userRepository.userExist(user)) {
            return userRepository.addUser(user);
        } else {
            return Response.status(400).entity("User name reserved.").build();
        }

    }

    public String checkLoginAndGetToken(User user) {
        User userWithID = userRepository.findByUserName(user.getName());
        if(userWithID != null) {
            if(loginRepository.isUserLoggedIn(userWithID.getUserID())) {
                return userWithID.getName() + " has a valid token.";
            } else if(getUserAddresses(userWithID.getUserID()).size() != 0) {
                if(userRepository.canLogIn(userWithID)) {

                    loginRepository.addLoginDateToUser(userWithID);
                    return GenerateToken.generateUserToken(userWithID.getName());
                } else {
                    return "Cannot log in.";
                }
            } else {
                return "User created, but you need register at least 1 address. /user/register_addresses";
            }
        } else {
            return "User does not exist.";
        }


    }

    public List<User> listUsers() {
        return userRepository.listAllUser();
    }

    public List<Address> getUserAddresses(Long id) {
        return addressRepository.getUserAddresses(userRepository.findById(id));
    }

    public Response logOut(SecurityContext ctx) {
        return tokenHashService.addValidTokenHash(ctx);
    }

}
