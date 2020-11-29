package com.example.punkbeer.models;

import java.util.UUID;

public class Users {
    String email, id;

    public Users(String email) {
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
}
