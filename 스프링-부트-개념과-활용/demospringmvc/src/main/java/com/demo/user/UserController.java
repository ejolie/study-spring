package com.demo.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/user")
    public User create() {
        return null;
    }
}
