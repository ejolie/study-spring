package com.ejolie.corespringsecurity.repository;

import com.ejolie.corespringsecurity.domain.entiry.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String IpAddress);
}
