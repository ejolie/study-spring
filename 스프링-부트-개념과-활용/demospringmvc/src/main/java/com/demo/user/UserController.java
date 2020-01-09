package com.demo.user;

import org.springframework.web.bind.annotation.*;

@RestController
// @RestController 사용시
// Return type 에서 @ResponseBody 생략 cf. HttpMessageConverters
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/users/create")
    public User create(@RequestBody User user) {
        return user;
    }
}
