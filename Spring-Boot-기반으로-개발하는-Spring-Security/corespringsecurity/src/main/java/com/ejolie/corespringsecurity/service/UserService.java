package com.ejolie.corespringsecurity.service;

import com.ejolie.corespringsecurity.domain.dto.AccountDto;
import com.ejolie.corespringsecurity.domain.entiry.Account;

import java.util.List;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);

    void order();
}
