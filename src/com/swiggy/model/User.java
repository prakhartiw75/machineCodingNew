package com.swiggy.model;

public class User {
    String userId;
    String name;
    String email;
    String phone;
    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
