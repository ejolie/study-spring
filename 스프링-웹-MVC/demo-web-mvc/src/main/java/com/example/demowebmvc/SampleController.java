package com.example.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    // 컨트롤러 안의 메서드: 핸들러 메서드
    @GetMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
