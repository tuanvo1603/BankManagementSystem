package com.example.authservice;

import com.example.authservice.dto.request.UserRequest;
import com.example.authservice.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.Rollback;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@Rollback(value = false)
class AuthServiceApplicationTests {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    void contextLoads() {
        UserRequest request = new UserRequest();
        request.setUsername("admin02");
        request.setPassword("1234");
        request.setFullName("hieu");
        request.setEmail("@1234");

        userDetailsService.saveUser(request);
    }

}
