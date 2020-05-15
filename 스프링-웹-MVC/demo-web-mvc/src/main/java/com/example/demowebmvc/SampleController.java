package com.example.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model /*, HttpSession httpSession */) {
        Event newEvent = new Event();
        newEvent.setLimit(50);

        model.addAttribute("event", newEvent);
        /* httpSession.setAttribute("event", newEvent); */

        return "events/form";
    }

    @PostMapping("/events")
    public String getEvent(@Validated @ModelAttribute Event event,
                           BindingResult bindingResult,
                           SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "events/form";
        }

        sessionStatus.setComplete();

        // DB save
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        // DB read
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute(eventList);

        return "/events/list";
    }
}