package com.niit.userproductservice.service;

import com.niit.userproductservice.exception.ProductAlreadyExistException;
import com.niit.userproductservice.exception.ProductNotFoundException;
import com.niit.userproductservice.exception.UserAlreadyExistsException;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.model.User;

import java.util.List;

public interface ProductService {
    public Product saveProducts(Product product) throws ProductAlreadyExistException;
    public Product updatePrice(Product product, int price) throws ProductNotFoundException;
    public Product getProductByProductID(int id) throws ProductNotFoundException;
    public boolean deleteProduct(Product product) throws ProductNotFoundException;
    public List<Product> getProductsInStock();
    User registerUser(User user) throws UserAlreadyExistsException;
}
