package com.example.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    // 컨트롤러 안의 메서드: 핸들러 메서드
    @GetMapping(value = "/hello", params = "name=keesun")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
