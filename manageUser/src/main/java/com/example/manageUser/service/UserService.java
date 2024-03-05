package com.example.manageUser.service;

import com.example.manageUser.enitity.User;
import com.example.manageUser.enitity.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long userId);
}
