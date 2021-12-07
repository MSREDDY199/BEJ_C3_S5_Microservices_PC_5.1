package com.niit.userproductservice.model;

import lombok.Data;

@Data
public class ProductDescription {
    private String productWeight;
    private int price;
    private int totalItems;

    public ProductDescription(String productWeight, int price, int totalItems) {
        this.productWeight = productWeight;
        this.price = price;
        this.totalItems = totalItems;
    }
}
