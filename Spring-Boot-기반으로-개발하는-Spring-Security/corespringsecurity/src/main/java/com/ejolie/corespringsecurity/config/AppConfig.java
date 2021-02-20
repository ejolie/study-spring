package com.ejolie.corespringsecurity.config;

import com.ejolie.corespringsecurity.repository.AccessIpRepository;
import com.ejolie.corespringsecurity.repository.ResourcesRepository;
import com.ejolie.corespringsecurity.service.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository, accessIpRepository);
        return securityResourceService;
    }
}
