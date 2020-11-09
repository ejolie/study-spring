package com.ejolie.corespringsecurity.domain;

import lombok.Data;

@Data
public class AccountDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private int age;

    private String role;
}
