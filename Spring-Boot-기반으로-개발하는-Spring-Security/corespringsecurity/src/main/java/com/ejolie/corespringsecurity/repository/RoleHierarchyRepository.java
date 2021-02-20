package com.ejolie.corespringsecurity.repository;


import com.ejolie.corespringsecurity.domain.entiry.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}
