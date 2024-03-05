package com.example.manageUser.repository;


import com.example.manageUser.enitity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
