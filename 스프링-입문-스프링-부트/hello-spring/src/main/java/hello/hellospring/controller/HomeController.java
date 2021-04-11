package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 1. 맵핑된 컨트롤러가 있는지 먼저 확인
     * 2. 없다면 static/index.html 리턴
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
