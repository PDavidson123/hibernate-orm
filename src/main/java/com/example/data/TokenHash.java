package com.example.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TokenHash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenID;
    private Long token;
    private Date expTime;

    public TokenHash() {

    }

    public TokenHash(Long token, Date date) {
        this.token = token;
        this.expTime = date;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
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
