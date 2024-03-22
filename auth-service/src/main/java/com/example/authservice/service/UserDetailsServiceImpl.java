package com.example.authservice.service;

import com.example.authservice.dto.UserResponseDTO;
import com.example.authservice.dto.request.UserRequest;
import com.example.authservice.exception.UserCanNotFoundException;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void saveUser(UserRequest userRequest){

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .fullName(userRequest.getFullName())
                .createdAt(new Date())
                .build();
        userRepository.save(user);
    }

    public UserResponseDTO getUserById(Long userId) throws UserCanNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null){
            throw new UserCanNotFoundException();
        }
        return new UserResponseDTO(user.getUserId(), user.getFullName());
    }
}
