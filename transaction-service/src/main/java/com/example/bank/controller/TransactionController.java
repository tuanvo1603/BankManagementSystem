package com.example.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class TransactionController {

    @GetMapping("/get-all-tracsaction")
    public ResponseEntity<?> getAllTransaction(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null) {
            return new ResponseEntity<>("test is null!",HttpStatus.OK);
        } else {

        }

        return new ResponseEntity<>("test transaction!",HttpStatus.OK);
    }


}
