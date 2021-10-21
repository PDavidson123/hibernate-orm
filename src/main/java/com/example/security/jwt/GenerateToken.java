package com.example.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {
    public static String generateDefaultToken() {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn("jdoe@quarkus.io")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .claim(Claims.birthdate.name(), "2051-07-13")
                        .sign();
        return token;
    }
    public static String generateVisitorToken() {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn("jdoe@quarkus.io")
                        .groups(new HashSet<>(Arrays.asList("Visitor")))
                        .claim(Claims.birthdate.name(), "2051-07-13")
                        .sign();
        return token;
    }
}
