package com.example.Repository;

import com.example.data.TokenHash;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.ws.rs.core.Response;

public class TokenHashRepository implements PanacheRepository<TokenHash> {

    public boolean addValidTokenHash(Long tokenHash) {
        try {
            persist(new TokenHash(tokenHash));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkValidTokenHash(Long tokenHash) {
        return true;
    }

    public boolean deleteTokenHash(Long tokenHash) {
        return true;
    }
}
