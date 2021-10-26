package com.example.Repository;

import com.example.data.Address;
import com.example.data.TokenHash;
import com.example.security.jwt.GenerateToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class TokenHashRepository implements PanacheRepository<TokenHash> {

    public boolean addValidTokenHash(TokenHash tokenhash) {
        try {
            persist(tokenhash);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TokenHash> getAllTokenHash() {
        return listAll();
    }

    public boolean isTokenLogout(Long tokenHash) {
        return !list("token", tokenHash).isEmpty();
    }

    public void deleteTokenHash(TokenHash tokenHash) {
        delete(tokenHash);
    }
}
