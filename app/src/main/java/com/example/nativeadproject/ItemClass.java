package com.example.nativeadproject;

public class ItemClass {

    private final int img;
    private final String name;
    private final String email;

    public ItemClass(int img, String name, String email) {
        this.img = img;
        this.name = name;
        this.email = email;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
