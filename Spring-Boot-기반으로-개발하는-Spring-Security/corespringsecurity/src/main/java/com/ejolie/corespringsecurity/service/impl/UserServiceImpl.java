package com.ejolie.corespringsecurity.service.impl;

import com.ejolie.corespringsecurity.domain.Account;
import com.ejolie.corespringsecurity.repository.UserRepository;
import com.ejolie.corespringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
