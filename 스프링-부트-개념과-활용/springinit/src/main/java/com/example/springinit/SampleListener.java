package com.example.springinit;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

// 이벤트 발생시점에 따라 등록방법이 다름
// - ApplicationStartingEvent
//   ApplicationContext가 생성되기 이전이므로 발생 X
//   SpringApplication.addEventListener()로 등록해줘야 함
// - ApplicationStartedEvent
//   @Component 붙여서 Bean으로 등록하기만 하면 발생 O
public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("=======================");
        System.out.println("Application is starting");
        System.out.println("=======================");
    }
}