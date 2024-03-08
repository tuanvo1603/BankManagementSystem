package com.example.userService.service.impl;


import com.example.userService.enitity.User;
import com.example.userService.enitity.UserRole;
import com.example.userService.exception.UserFoundException;
import com.example.userService.repository.RoleRepository;
import com.example.userService.repository.UserRepository;
import com.example.userService.service.TimeService;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TimeService timeService;

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {


        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            String currentTime = timeService.getCurrentTime().getDateTime();
            Date date = timeService.convertToDate(currentTime);
            user.setCreated_at(date);
            local = this.userRepository.save(user);
        }

        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }


}
