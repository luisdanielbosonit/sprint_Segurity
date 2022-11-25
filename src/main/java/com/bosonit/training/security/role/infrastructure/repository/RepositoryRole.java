package com.bosonit.training.security.role.infrastructure.repository;


import com.bosonit.training.security.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface RepositoryRole extends JpaRepository<Role, Integer> {
    Role findByNameRole(String nameRole);
}
