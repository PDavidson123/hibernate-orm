package com.example.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TokenHash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenID;
    private Long token;

    public TokenHash() {

    }

    public TokenHash(Long token) {
        this.token = token;
    }

    public Long getTokenID() {
        return tokenID;
    }

    public void setTokenID(Long tokenID) {
        this.tokenID = tokenID;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }
}
