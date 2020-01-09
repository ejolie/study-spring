package com.example.springinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringinitApplication {

	@ConfigurationProperties("server")
	@Bean
	public ServerProperties serverProperties() {
		return new ServerProperties();
	}

	public static void main(String[] args) {
		// ApplicationStartingEvent 이벤트를 발생시키기 위함
//		SpringApplication app = new SpringApplication(SpringinitApplication.class);
//		app.addListeners(new SampleListener());
//		app.run(args);

		SpringApplication.run(SpringinitApplication.class, args);
	}

}
