package com.example.authservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    List<String> roles;
}
