package com.example.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class SampleController {

    // 컨트롤러 안의 메서드: 핸들러
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
