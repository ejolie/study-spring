package com.ejolie.corespringsecurity.repository;

import com.ejolie.corespringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
