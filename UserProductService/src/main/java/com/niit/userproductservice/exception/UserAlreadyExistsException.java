package com.niit.userproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND,reason = "user already exists ...")
public class UserAlreadyExistsException extends Exception{
}
