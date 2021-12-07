package com.niit.userproductservice.service;

import com.niit.rabbitmq.model.UserDTO;
import com.niit.userproductservice.config.Producer;
import com.niit.userproductservice.exception.ProductAlreadyExistException;
import com.niit.userproductservice.exception.ProductNotFoundException;
import com.niit.userproductservice.exception.UserAlreadyExistsException;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.model.User;
import com.niit.userproductservice.repository.ProductRepository;
import com.niit.userproductservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    Producer producer;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNo(user.getPhoneNo());
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            userRepository.save(user);
            producer.sendMessageToRabbitMq(userDTO);
        }
        return user;
    }

    @Override
    public Product saveProducts(Product product) throws ProductAlreadyExistException {
        if(productRepository.findById(product.getProductCode()).isPresent()){
            throw new ProductAlreadyExistException();
        }
        else {
            return (Product) productRepository.save(product);
        }
    }

    @Override
    public Product updatePrice(Product product, int price) throws ProductNotFoundException{
        if(productRepository.findById(product.getProductCode()).isEmpty()){
            throw new ProductNotFoundException();
        }
        else {
            product.getProductDescription().setPrice(price);
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public Product getProductByProductID(int id) throws ProductNotFoundException{
        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }
        else {
            return productRepository.findById(id).get();
        }
    }

    @Override
    public boolean deleteProduct(Product product) throws ProductNotFoundException{
        boolean condition = productRepository.findById(product.getProductCode()).isEmpty();
        if(condition)
            throw new ProductNotFoundException();
        else
            productRepository.delete(product);
        return true;
    }

    @Override
    public List<Product> getProductsInStock(){
        return productRepository.getProductsInStock();
    }

}
