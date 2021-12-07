package com.niit.userproductservice.service;

import com.niit.userproductservice.exception.UserNotFoundException;
import com.niit.userproductservice.modal.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User findByUsernameAndPassword(String username,String password) throws UserNotFoundException;
    public List<User> getAllUsers();
}
