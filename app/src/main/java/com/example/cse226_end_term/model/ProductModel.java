package com.example.cse226_end_term.model;

public class ProductModel {

    String name,price,description;
    int image;

    public ProductModel(String name, String price, String description, int image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
