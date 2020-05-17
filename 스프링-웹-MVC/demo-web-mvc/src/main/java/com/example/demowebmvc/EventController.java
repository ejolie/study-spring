package com.example.demowebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

    @Autowired
    EventValidator eventValidator;

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

    // 방법 2.
    @ModelAttribute("anotherCategories")
    public List<String> anotherCategories(Model model) {
        return Arrays.asList("study", "seminar", "hobby", "social");
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "events/form-name";
        }
        eventValidator.validate(event, bindingResult);

        // DB save
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        // 세션에 있는 event를 넘겨준다.
        model.addAttribute("event", event);
        return "events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "events/form-limit";
        }
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("newEvent", event);
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(/* @ModelAttribute Event newEvent, */
                            Model model,
                            @SessionAttribute LocalDateTime visitTime) {
        // cf. @SessionAttribute 사용 안하고 HttpSession 이용하는 방법
        // LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
        System.out.println(visitTime);

        // DB read
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        // Flash Attribute
        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(newEvent);

        model.addAttribute(eventList);
        return "/events/list";
    }
}