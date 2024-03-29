package com.example.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackage().getName());
        return jaxb2Marshaller;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // order 숫자가 낮을수록 우선순위가 높다
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi")
                .order(-1);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/mobile/**")
//                .addResourceLocations("classpath:/mobile/", "file:/Users/keesun/files/")
//                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/hi").setViewName("hi");
//    }
}
