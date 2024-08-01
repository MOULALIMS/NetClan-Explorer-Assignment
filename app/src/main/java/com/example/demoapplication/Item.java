package com.example.demoapplication;
public class Item {
    private String user;
    private String location;

    public Item(String user, String location) {
        this.user = user;
        this.location = location;
    }

    public String getName() {
        return user;
    }

    public String getLocation() {
        return location;
    }
}
