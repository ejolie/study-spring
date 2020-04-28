package com.example.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @GetMapping("/events")
    @ResponseBody
    public String getEvents() {
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getEvent(@PathVariable int id) {
        return "event";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable int id) {
        return "deleted";
    }

    // 컨트롤러 안의 메서드: 핸들러 메서드
    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }
}