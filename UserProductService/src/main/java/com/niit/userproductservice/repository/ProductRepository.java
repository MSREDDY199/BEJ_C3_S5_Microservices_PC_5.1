package com.niit.userproductservice.repository;

import com.niit.userproductservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Integer> {
    @Query("{'productDescription.totalItems':{'$gt':0}}")
    List<Product> getProductsInStock();
}
