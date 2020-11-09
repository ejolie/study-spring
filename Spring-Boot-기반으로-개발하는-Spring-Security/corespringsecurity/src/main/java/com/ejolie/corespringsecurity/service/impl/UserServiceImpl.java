package com.ejolie.corespringsecurity.service.impl;

import com.ejolie.corespringsecurity.domain.Account;
import com.ejolie.corespringsecurity.repository.UserRepository;
import com.ejolie.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private static UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
