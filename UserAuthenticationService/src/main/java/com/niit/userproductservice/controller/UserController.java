package com.niit.userproductservice.controller;

import com.niit.userproductservice.exception.UserAlreadyExistsException;
import com.niit.userproductservice.exception.UserNotFoundException;
import com.niit.userproductservice.modal.User;
import com.niit.userproductservice.service.SecurityTokenGenerator;
import com.niit.userproductservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity responseEntity;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try {
            User user1 = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
            if (user1.getUsername().equals(user.getUsername())){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity("internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException{
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
}
