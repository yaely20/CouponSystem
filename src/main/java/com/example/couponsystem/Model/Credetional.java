package com.example.couponsystem.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Credetional {
    private  String email;
    private  String password;

    public Credetional() {
    }

    public Credetional(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credetional{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
