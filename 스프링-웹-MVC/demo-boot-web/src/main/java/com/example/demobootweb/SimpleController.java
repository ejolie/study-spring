package com.example.demobootweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @GetMapping("/hi")
    @ResponseBody
    public String hi() {
        return "hi";
    }
}
