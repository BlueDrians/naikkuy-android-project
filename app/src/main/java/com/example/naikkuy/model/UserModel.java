package com.example.naikkuy.model;

public class UserModel {
    private final String name;
    private final String username;
    private final String phone;

    public UserModel(String name, String username, String phone) {
        this.name = name;
        this.username = username;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }
}
