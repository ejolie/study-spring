package com.example.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class EventUpdateController {

    @PostMapping("/events")
    @ResponseBody
    public String createEvent() {
        return "created";
    }

    @PutMapping("/events/{id}")
    @ResponseBody
    public String updateEvent(@PathVariable int id) {
        return "updated";
    }
}
