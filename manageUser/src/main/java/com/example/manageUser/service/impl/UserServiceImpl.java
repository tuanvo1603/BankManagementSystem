package com.example.manageUser.service.impl;


import com.example.manageUser.enitity.Role;
import com.example.manageUser.enitity.User;
import com.example.manageUser.enitity.UserRole;
import com.example.manageUser.helper.UserFoundException;
import com.example.manageUser.repository.RoleRepository;
import com.example.manageUser.repository.UserRepository;
import com.example.manageUser.service.TimeService;
import com.example.manageUser.service.UserService;
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
