package com.example.springinit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private KeesunProperties keesunProperties;

    @Autowired
    private String hello;

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("======================");
        logger.debug(hello);
        logger.debug(keesunProperties.getName());
        logger.debug(keesunProperties.getFullName());
        logger.debug("======================");

        System.out.println("======================");
        System.out.println(keesunProperties.getName());
        System.out.println(keesunProperties.getAge());
        System.out.println("======================");
    }
}