package com.niit.userproductservice.config;

import com.niit.rabbitmq.modal.UserDTO;
import com.niit.userproductservice.exception.UserNotFoundException;
import com.niit.userproductservice.modal.User;
import com.niit.userproductservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {
    @Autowired
    private UserServiceImpl userService;

    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserNotFoundException{
        User user=new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhoneNo(userDto.getPhoneNo());
        userService.saveUser(user);
    }
}
