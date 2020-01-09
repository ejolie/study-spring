package com.example.springinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {
    // in application.properties
//    @Value("${keesun.name}")
//    private String name;
//
//    @Value("${keesun.age}")
//    private int age;

    @Autowired
    KeesunProperties keesunProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("======================");
        System.out.println(keesunProperties.getName());
        System.out.println(keesunProperties.getAge());
        System.out.println("======================");
    }
}