package com.example.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {
    public static String generateDefaultToken() {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn("username")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .expiresIn(1000*60*60)
                        .sign();
        return token;
    }

    public static String generateUserToken(String username) {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn(username)
                        .groups(new HashSet<>(Arrays.asList("User")))
                        .expiresIn(1000*60*60)
                        .sign();
        return token;
    }
}
