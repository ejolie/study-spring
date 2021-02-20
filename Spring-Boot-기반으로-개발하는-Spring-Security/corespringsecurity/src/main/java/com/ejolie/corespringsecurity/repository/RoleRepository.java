package com.ejolie.corespringsecurity.repository;

import com.ejolie.corespringsecurity.domain.entiry.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}