package com.fk.entities.models;

import java.util.Map;

public class Price {
    private Double mrp;
    private Double finalPrice;
    private Double listPrice;
    public Price() {
    }

    public void deserialize(Map<String, Object> pnpResponse) {
        // TODO : Create Price entity from pnpResponse
        this.mrp = (Double) pnpResponse.getOrDefault("mrp", 299.00);
    }
}
