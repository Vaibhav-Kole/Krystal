package com.fk.entities.models;

import java.util.Map;

public class Listing {

    private String pid;
    private String lid;
    private String sellerId;
    private String brand;
    private String title;
    private Double mrp;
    private Double finalPrice;
    private Double listPrice;

    public Listing(String pid, String lid) {
        this.pid = pid;
        this.lid = lid;
    }

    public void deserializeZuluResponse(Map<String, Object> zuluResponse) {
        this.brand = (String) zuluResponse.getOrDefault("brand", "Flipkart");
        this.sellerId = (String) zuluResponse.getOrDefault("sellerId", "FlipkartSeller");
    }

    public void deserializePnPResponse(Map<String, Object> pnpResponse) {
        this.mrp = (Double) pnpResponse.getOrDefault("mrp", 299.00);
        this.finalPrice = (Double) pnpResponse.getOrDefault("fsp", 269.00);
        this.listPrice = (Double) pnpResponse.getOrDefault("asp", 279.00);

    }
}
