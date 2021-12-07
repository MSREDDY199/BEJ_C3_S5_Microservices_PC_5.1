package com.niit.userproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product already exists in inventory")
public class ProductAlreadyExistException extends Exception{
}
