package com.example.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import com.example.userService.controller.UserController;
import com.example.userService.enitity.Role;
import com.example.userService.enitity.User;
import com.example.userService.enitity.UserRole;
import com.example.userService.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testCreateUser() throws Exception {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");
        mockUser.setEmail("test@example.com");
        mockUser.setFull_name("Test User");

        // Mocking bcrypt password encoder
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Mocking role and userRole
        Role role = new Role();
        role.setRole_id(45L);
        role.setName("customer");

        UserRole userRole = new UserRole();
        userRole.setUser(mockUser);
        userRole.setRole(role);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);

        // Mocking userService.createUser()
        when(userService.createUser(any(User.class), anySet())).thenReturn(mockUser);

        // Call the controller method
        User createdUser = userController.createUser(mockUser);

        // Verify that the userService.createUser() method was called with the correct arguments
        verify(userService, times(1)).createUser(mockUser, roles);

        // Assert the result
        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());
        assertEquals("encodedPassword", createdUser.getPassword());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("Test User", createdUser.getFull_name());
    }
}

