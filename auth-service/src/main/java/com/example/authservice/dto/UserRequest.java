package com.example.authservice.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    @Size(min = 5, message = "fheihfoiehf 5 ki yse")
    private String username;
    @Size(min = 5, message = "fheihfoiehf 8 ki yse")
    private String password;
    private String email;
    private String fullName;
}
