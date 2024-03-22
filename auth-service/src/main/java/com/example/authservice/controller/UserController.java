package com.example.authservice.controller;

import com.example.authservice.dto.request.UserRequest;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.model.Role;
import com.example.authservice.dto.UserResponseDTO;
import com.example.authservice.exception.UserCanNotFoundException;
import com.example.authservice.service.UserDetailsServiceImpl;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.create(userRequest));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return ResponseEntity.ok().body(userService.update(userRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/role/{userId}")
    public ResponseEntity<Set<Role>> getRoleUser(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.getRoleUser(userId));
    }

    @PostMapping("/role/assign/{userId}")
    public ResponseEntity<String> assignRole(@PathVariable Long userId, @RequestBody Role role){
        userService.assignRole(userId, role.getRoleId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/role/revoke/{userId}")
    public ResponseEntity<String> revoke(@PathVariable Long userId, @RequestBody Role role){
        userService.revokeRole(userId, role.getRoleId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
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
