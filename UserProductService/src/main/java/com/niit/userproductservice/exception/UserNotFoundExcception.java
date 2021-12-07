package com.niit.userproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "user not found ...")
public class UserNotFoundExcception extends Exception{
}
