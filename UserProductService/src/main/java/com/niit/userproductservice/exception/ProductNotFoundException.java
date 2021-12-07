package com.niit.userproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product not Found")
public class ProductNotFoundException extends Exception{
}
