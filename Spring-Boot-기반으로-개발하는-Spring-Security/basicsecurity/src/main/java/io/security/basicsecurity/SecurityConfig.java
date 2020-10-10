package io.security.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. 인가
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        // 2. 인증
        // 로그인
        http
                .formLogin()
//            .loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("userPwd")
                .loginProcessingUrl("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("authentication : " + authentication.getName());
                    httpServletResponse.sendRedirect("/");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    System.out.println("exception : " + e.getMessage());
                    httpServletResponse.sendRedirect("/login");
                })
                .permitAll(); // loginPage에는 누구나 접근가능하게 만듦

        // 로그아웃
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    HttpSession session = httpServletRequest.getSession();
                    session.invalidate();
                })
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.sendRedirect("/login");
                })
                .deleteCookies("remember-me");

        // Remember-me
        http
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600);
//                .alwaysRemember(true)
//                .userDetailsService(userDetailsService);
    }
}
