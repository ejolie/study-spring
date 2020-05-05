package com.example.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    /**
     * Handler Interceptor
     * -> preHandle 1
     * -> preHandle 2
     * -> 요청 처리
     * -> postHandle 2
     * -> postHandle 1
     * -> 뷰 렌더링
     * -> afterCompletion 2
     * -> afterCompletion 1
     */

    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }

    @GetMapping("/message")
    // @ResponseBody
    public String message(@RequestBody String body) {
        return body;
    }
}
