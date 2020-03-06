package com.example.springtestdemo.sample;

import javafx.beans.binding.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 통합 테스트
// @WebMvcTest(SampleController.class) // Conroller 하나만 테스트 + MockMvc
@AutoConfigureMockMvc
public class SampleControllerTest {

    // 1. WebEnvrionment.MOCK + MockMvc
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello keesun"))
                .andDo(print());
    }

    // 2. WebEnvironment.RANDOM_PORT + TestRestTemplate
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void hello2() throws Exception {
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello keesun");
    }

    // 3. + MockBean
    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello3() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");

        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello keesun");
    }

    // 4. + WebTestClient @ WebFlux
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void hello4() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello whiteship");
    }
}