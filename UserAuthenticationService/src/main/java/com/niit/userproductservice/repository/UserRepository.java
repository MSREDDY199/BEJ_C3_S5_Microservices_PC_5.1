package com.niit.userproductservice.repository;

import com.niit.userproductservice.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUsernameAndPassword(String username, String password);
}
