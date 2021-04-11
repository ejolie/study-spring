package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    /**
     * MVC 패턴: Controller - View 분리
     * cf. Model 1: View(JSP) 안에서 모든 일을 다 함
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * @ResponseBody
     * - HTTP Response Body 에 내용을 직접 작성하겠다.
     * - ViewResolver 대신 HttpMessageConverter 가 동작
     * - 기본 문자 처리: StringHttpMessageConverter
     * - 기본 객체 처리: MappingJackson2HttpMessageConverter
     *   cf. JSON 처리 대표 라이브러리
     *     1) Jackson: 스프링 기본
     *     2) Gson
     * - 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) { // return: 문자 -> plain text
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) { // return: 객체 -> JSON
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello { // Java Bean 표준 방식, 프로퍼티 접근 방식
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
