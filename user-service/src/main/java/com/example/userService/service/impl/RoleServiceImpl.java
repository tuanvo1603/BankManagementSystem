package com.example.userService.service.impl;

import com.example.userService.enitity.Role;
import com.example.userService.exception.RoleFoundException;
import com.example.userService.repository.RoleRepository;
import com.example.userService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) throws RoleFoundException {
        if (roleRepository.existsById(role.getRole_id())){
            throw new RoleFoundException();
        }
        Role roleCreated = roleRepository.save(role);
        return roleCreated;
    }

    @Override
    public String removeRole(Long roleId) {
        roleRepository.deleteById(roleId);
        return null;
    }
}
