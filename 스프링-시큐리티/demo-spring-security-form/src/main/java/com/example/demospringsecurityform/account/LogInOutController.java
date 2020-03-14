package com.example.demospringsecurityform.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInOutController {

    @GetMapping("/login")
    public String logInForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logOutForm() {
        return "logout";
    }
}
