package com.ejolie.corespringsecurity.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    @GetMapping("/login")
    public String login() {
        return "user/login/login";
    }
}
