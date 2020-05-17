package com.example.demowebmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

/**
 * 전역 컨트롤러
 * - 예외 처리, 바인딩 설정, 모댈 객체를 모든 컨트롤러 전면에 걸쳐 적용하고 싶은 경우에 사용한다.
 * - 적용할 범위를 지정할 수도 있다.
 */
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {

    // 특정 예외가 발생한 요청을 처리하는 핸들러를 정의한다.
    @ExceptionHandler
    public String eventErrorHandler(EventException exception, Model model) {
        model.addAttribute("message", "event error");
        return "error";
    }

    @ExceptionHandler
    public String runtimeErrorHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "runtime error");
        return "error";
    }

    // 특정 컨트롤러에서 바인딩 또는 검증 설정을 변결하고 싶을 때 사용한다.
    // event 모델에 대한 바인더
    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        // webDataBinder.addValidators(new EventValidator());
    }

    // 모든 View 에서 해당 Model 정보를 사용한다면 다음과 같이 정의할 수 있다.
    // 방법 1. 하나의 메서드에서 여러 개의 모델을 추가할 수 있다.
    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", Arrays.asList("study", "seminar", "hobby", "social"));
    }
}
