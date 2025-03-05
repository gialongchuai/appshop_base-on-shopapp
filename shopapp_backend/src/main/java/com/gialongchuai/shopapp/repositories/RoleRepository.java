package com.gialongchuai.shopapp.repositories;

import com.gialongchuai.shopapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
