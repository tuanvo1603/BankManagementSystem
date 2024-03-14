package com.example.userService.service;

import com.example.userService.enitity.Role;
import com.example.userService.exception.RoleFoundException;

public interface RoleService {
    Role createRole(Role role) throws RoleFoundException;
    String removeRole(Long roleId);
}
