package com.example.userService.repository;


import com.example.userService.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where user_id = :userId", nativeQuery = true)
    User getUserById(Long userId);
    User findByUsername(String username);
}
