package com.example.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/form";
    }

    @PostMapping("/events/name/{name}")
    @ResponseBody
    public Event getEvent(@Validated(Event.ValidateLimit.class) @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("===========================");
            bindingResult.getAllErrors().forEach(err -> {
                System.out.println(err.toString());
            });
        }
        return event;
    }
}