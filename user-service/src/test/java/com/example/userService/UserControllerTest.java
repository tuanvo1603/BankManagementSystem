package com.example.userService;

import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Rollback(false)
    @Test
    public void testCreateUserAdmin() throws Exception {
        User user = new User();
        user.setUsername("admin2002");
        user.setPassword("1234");
        user.setEmail("admin02@mail.com");
        user.setFull_name("Man city");

        User u = userController.createUser(user);


        assertTrue(u != null );
    }


    @Test
    @Rollback(value = false)
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
        role.setRole_id(45);
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
        assertEquals("encodedPasswor", createdUser.getPassword());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("Test User", createdUser.getFull_name());
    }
}

