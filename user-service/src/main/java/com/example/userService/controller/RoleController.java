package com.example.userService.controller;

import com.example.userService.enitity.Role;
import com.example.userService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        Role roleRespone = new Role();
        try {
            roleRespone = roleService.createRole(role);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>( roleRespone,HttpStatus.CREATED);
    };

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId){
        try {
            roleService.removeRole(roleId);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
