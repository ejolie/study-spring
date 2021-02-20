package com.ejolie.corespringsecurity.service;

import com.ejolie.corespringsecurity.domain.entiry.Role;

import java.util.List;

public interface RoleService {

    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}