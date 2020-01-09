package com.example.springinit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest
class SpringinitApplicationTests {

	@Autowired
	Environment environment;

	@Test
	void contextLoads() {
		assertThat(environment.getProperty("keesun.name"))
				.isEqualTo("keesun2");
	}

}
