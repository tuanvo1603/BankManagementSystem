package com.example.userService.service;

import com.example.userService.enitity.User;
import com.example.userService.enitity.UserRole;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    User getUser(String username);

    //delete user by id
    void deleteUser(Long userId);

    User updateUser(User user);
}
