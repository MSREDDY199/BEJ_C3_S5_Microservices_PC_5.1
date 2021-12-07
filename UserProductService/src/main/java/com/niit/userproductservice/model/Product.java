package com.niit.userproductservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Product {
    @Id
    private int productCode;
    private String productName;
    private ProductDescription productDescription;

    public Product(int productCode, String productName, ProductDescription productDescription) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
    }
}
