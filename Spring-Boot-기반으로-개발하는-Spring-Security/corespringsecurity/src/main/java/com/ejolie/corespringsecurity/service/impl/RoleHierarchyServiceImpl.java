package com.ejolie.corespringsecurity.service.impl;

import com.ejolie.corespringsecurity.domain.entiry.RoleHierarchy;
import com.ejolie.corespringsecurity.repository.RoleHierarchyRepository;
import com.ejolie.corespringsecurity.service.RoleHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    @Autowired
    private RoleHierarchyRepository roleHierarchyRepository;

    @Transactional
    @Override
    public String findAllHierarchy() {
        List<RoleHierarchy> rolesHierarchyList = roleHierarchyRepository.findAll();
        StringBuilder concatedRoles = new StringBuilder();
        for (RoleHierarchy roleHierarchy : rolesHierarchyList) {
            if (roleHierarchy.getParentName() != null) {
                concatedRoles.append(roleHierarchy.getParentName().getChildName());
                concatedRoles.append(" > ");
                concatedRoles.append(roleHierarchy.getChildName());
                concatedRoles.append("\n");
            }
        }

        return concatedRoles.toString();
    }
}
