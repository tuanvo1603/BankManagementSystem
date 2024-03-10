package com.example.userService.controller;

import com.example.userService.service.UserService;
import com.example.userService.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/internal")
public class InternalController {

    private final UserServiceImpl userService;

    @GetMapping("/existed-user/{userId}")
    public boolean isExistedUserById(@PathVariable Long userId) {
        return userService.isExistedUserById(userId);
    }
}
