package com.example.demo.login.repository;

import com.example.demo.login.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}
