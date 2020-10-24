package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Order(0)
@Configuration
@EnableWebSecurity
public class SecurityConfigMulti extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}

// Order(0) 으로 하면 /admin 접속 시 로그인 필요 없어짐
// 따라서, 구체적인 URL 범위를 먼저 처리하도록 우선순위를 높여야 함
@Order(1)
@Configuration
class SecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }
}
