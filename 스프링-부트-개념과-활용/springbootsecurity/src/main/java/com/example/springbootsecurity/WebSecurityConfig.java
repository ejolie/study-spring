package com.example.springbootsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// WebSecurityConfigurerAdapter 를 extends 하여
// AutoConfiguration 를 쓰지 않고 나만의 설정을 만듦
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

}
