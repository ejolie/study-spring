package com.ejolie.corespringsecurity.service;

import com.ejolie.corespringsecurity.domain.entiry.Resources;

import java.util.List;

public interface ResourcesService {

    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}