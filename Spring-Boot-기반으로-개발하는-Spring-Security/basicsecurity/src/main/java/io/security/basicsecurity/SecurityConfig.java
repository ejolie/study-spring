package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS", "USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN", "SYS", "USER");
        // -> Role Hierarchy 기능을 활용한다면 모든 권한을 부여하지 않아도 ADMIN 역할만으로 충분함
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. 인가
        // 권한 설정
        http
                .authorizeRequests()
//                .antMatchers("/login").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
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
                .successHandler((request, response, authentication) -> {
                    System.out.println("authentication : " + authentication.getName());

                    // 인증 예외가 발생하기 전의 요청 정보를 인증 성공 후 꺼냄
                    RequestCache requestCache = new HttpSessionRequestCache();
                    SavedRequest savedRequest = requestCache.getRequest(request, response);
                    String redirectUrl = savedRequest.getRedirectUrl();
                    response.sendRedirect(redirectUrl);
                })
                .failureHandler((request, response, e) -> {
                    System.out.println("exception : " + e.getMessage());
                    response.sendRedirect("/login");
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
                .tokenValiditySeconds(3600)
//                .alwaysRemember(true)
                .userDetailsService(userDetailsService);

        // 세션 관리
        http
                // 동시 세션 제어
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true) // true: 현재 사용자 인증 실패, false: 이전 사용자 세션 만료 (default)
        .and()
                // 세션 고정 보호
                .sessionFixation().changeSessionId();

        // 3. 예외 처리
        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/denied");
                });
    }
}
