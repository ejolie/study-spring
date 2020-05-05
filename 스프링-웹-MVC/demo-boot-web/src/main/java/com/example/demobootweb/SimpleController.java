package com.example.demobootweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }
}
