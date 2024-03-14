package com.example.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @GetMapping("/authentication/user")
    public ResponseEntity<String> user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println(principal.getName());
        return ResponseEntity.ok("OK " + principal.getName());
    }
}
