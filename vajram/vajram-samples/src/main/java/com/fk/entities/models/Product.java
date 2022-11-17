package com.fk.entities.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Product {

    private String pid;
    private String title;
    private String description;
    private String brand;
    private String vertical;
    private List<String> listings;

    public Product(String pid, String title, String description, String brand, String vertical,
            List<String> listings) {
        this.pid = pid;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.vertical = vertical;
        this.listings = new ArrayList<>(listings);
    }

    public Product(String pid) {
        this.pid = pid;
    }

    public List<String> getListings() {
        return Collections.unmodifiableList(listings);
    }

    public void setListings(List<String> listings) {
        this.listings = new ArrayList<>(listings);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public void deserialize(Map<String, Object> zuluResponse) {
        this.brand = (String) zuluResponse.getOrDefault("brand", "Flipkart");
        this.listings = (List<String>) zuluResponse.getOrDefault("listings", Collections.emptyList());
    }
}
