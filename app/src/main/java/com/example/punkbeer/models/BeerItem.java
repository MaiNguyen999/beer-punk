package com.example.punkbeer.models;

public class BeerItem {
    private String imageUrl;
    private String name;
    private String description;

    public String getDescription(){
        return description;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getName(){
        return name;
    }

    public BeerItem(String name,String imageUrl,String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
