package com.example.manageUser.controller;


import com.example.manageUser.config.JwtUtils;
import com.example.manageUser.enitity.DateTimeZone;
import com.example.manageUser.enitity.JwtRequest;
import com.example.manageUser.enitity.response.JwtResponse;
import com.example.manageUser.enitity.User;
import com.example.manageUser.enitity.response.UserActivityLog;
import com.example.manageUser.helper.UserNotFoundException;
import com.example.manageUser.kafka.KafkaUserPublisher;
import com.example.manageUser.service.TimeService;
import com.example.manageUser.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private KafkaUserPublisher publisher;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    TimeService timeService;


    //generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }
        /////////////authenticate
        User userDetails = (User) this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        String dateTimeZone = timeService.getCurrentTime().getDateTime();
        UserActivityLog activityLog = new UserActivityLog(userDetails.getUser_id(),"Login",dateTimeZone);
        publisher.sendLogUserToTopic(activityLog);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    //return the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));

    }



}
