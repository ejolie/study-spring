package io.security.basicsecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String index(HttpSession httpSession) {
        // 1. SecurityContextHolder 에서 인증 객체 가져오기
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        // 2. (인증 후) HttpSession 에서 인증 객체 가져오기
        SecurityContext securityContext = (SecurityContext) httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication authentication2 = securityContext.getAuthentication();

        return "home";
    }

    @GetMapping("/thread")
    public String thread() {
        // 자식 스레드
        // - 기본값인 MODE_THREADLOCAL 전략에서는 메인 스레드와 컨텍스트를 공유하지 않음
        new Thread(() -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        }).start();

        return "thread";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/pay")
    public String adminPay() {
        return "adminPay";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/denied")
    public String denied() {
        return "Access is denied";
    }
}
