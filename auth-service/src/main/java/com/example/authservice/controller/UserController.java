package com.example.authservice.controller;

import com.example.authservice.dto.UserRequest;
import com.example.authservice.dto.UserResponseDTO;
import com.example.authservice.exception.UserCanNotFoundException;
import com.example.authservice.model.User;
import com.example.authservice.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/users/new")
    public ResponseEntity<String> createUser(@ModelAttribute UserRequest userRequest){
        try {
            userDetailsService.saveUser(userRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDetails> getUser(@PathVariable String username){
        return ResponseEntity.ok().body(userDetailsService.loadUserByUsername(username));
    }

    @GetMapping("/users/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) throws UserCanNotFoundException {
        UserResponseDTO user = userDetailsService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
