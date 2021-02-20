package com.ejolie.corespringsecurity.repository;

import com.ejolie.corespringsecurity.domain.entiry.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    int countByUsername(String username);
}
