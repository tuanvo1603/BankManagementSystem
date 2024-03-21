package com.example.authservice.mapper;

import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.model.User;

public interface UserMapper {
    UserResponse toUserResponse(User user);
}
