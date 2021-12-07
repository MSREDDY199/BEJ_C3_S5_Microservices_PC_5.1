package com.niit.userproductservice.service;

import com.niit.userproductservice.modal.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
