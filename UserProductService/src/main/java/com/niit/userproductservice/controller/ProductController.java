package com.niit.userproductservice.controller;

import com.niit.userproductservice.exception.ProductAlreadyExistException;
import com.niit.userproductservice.exception.ProductNotFoundException;
import com.niit.userproductservice.exception.UserAlreadyExistsException;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.model.User;
import com.niit.userproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/")
public class ProductController {
    @Autowired
    private ProductService productService;
    private ResponseEntity responseEntity;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(productService.registerUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    @PostMapping("book/add")
    public ResponseEntity addNewProduct(@RequestBody Product product) throws ProductAlreadyExistException {
        try {
            productService.saveProducts(product);
            responseEntity = new ResponseEntity("Product added successfully", HttpStatus.CREATED);
        }
        catch (ProductAlreadyExistException p) {
            throw new ProductAlreadyExistException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error while making entry", HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PutMapping("book/update/{price}")
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable int price) throws ProductNotFoundException {
        try {
            productService.updatePrice(product, price);
            responseEntity = new ResponseEntity("Product Updated successfully", HttpStatus.OK);
        }
        catch (ProductNotFoundException p){
            throw new ProductNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error while Updating", HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("book/product/{id}")
        public ResponseEntity getProductByID(@PathVariable int id) throws ProductNotFoundException{
        try{
            Product product = productService.getProductByProductID(id);
            responseEntity = new ResponseEntity(product, HttpStatus.OK);
        }
        catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error while making entry", HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @DeleteMapping("book/delete")
    public ResponseEntity deleteByID(@RequestBody Product product) throws ProductNotFoundException{
        try{
            productService.deleteProduct(product);
            responseEntity = new ResponseEntity("Product deleted successfully", HttpStatus.OK);
        }
        catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error while deleting product", HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("book/stock")
    public ResponseEntity getAllProductsInStock(){
        return responseEntity = new ResponseEntity(productService.getProductsInStock(), HttpStatus.OK);
    }
}
