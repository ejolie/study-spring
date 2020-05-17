package com.example.demowebmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    /**
     *  BindingResult
     *  - 사용 O : status 200, 에러 메시지 출력
     *  - 사용 X : status 400
     * @param event
     * @param bindingResult
     * @return Event
     */
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event,
                                             BindingResult bindingResult) {
        // DB save
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        // return ResponseEntity.ok().body(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }
}
