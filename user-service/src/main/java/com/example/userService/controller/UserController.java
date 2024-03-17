package com.example.userService.controller;


import com.example.userService.enitity.Role;
import com.example.userService.enitity.User;
import com.example.userService.enitity.UserRole;

import com.example.userService.exception.UserFoundException;
import com.example.userService.exception.UserNotFoundException;
import com.example.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@RestController()
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return "Ok Test" + userId;
    }

    @GetMapping("/test1")
    public String test1(){
        return "Ok Test1";
    }


    @GetMapping("/admin")
    public String admin(){
        return "Role admin";
    }


    @GetMapping("/manage")
    public String manage(){
        return "Role manage";
    }

    //creating user
    @PostMapping("/register")
    public User createUser(@RequestBody User user) throws Exception {
        //encoding password with bcryptpasswordencoder
//        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRole_id(45L);
        role.setName("customer");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    @GetMapping("/userinfo/{id}")
    public User getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return this.userService.getUserFromId(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }


}
