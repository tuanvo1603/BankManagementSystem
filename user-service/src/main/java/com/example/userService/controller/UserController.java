package com.example.userService.controller;


import com.example.userService.enitity.Role;
import com.example.userService.enitity.User;
import com.example.userService.enitity.UserRole;
import com.example.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/test")
//    public String test(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//        final String jwt = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
//        System.out.println(jwt);
//        return "Ok Test" + userId + " token: " + jwt;
//    }

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
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        //encoding password with bcryptpasswordencoder
//        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
//        role.setRole_id(45);
        role.setName("customer");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }


//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//        return ResponseEntity.ok(ex.getMessage());
//    }


}
