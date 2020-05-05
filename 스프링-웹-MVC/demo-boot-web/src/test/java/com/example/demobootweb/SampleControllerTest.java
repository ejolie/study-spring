package com.example.demobootweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Marshaller marshaller;

    @Test
    public void hello() throws Exception {
        Person person = new Person();
        person.setName("keesun");
        Person savedPerson = personRepository.save(person);

        mockMvc.perform(get("/hello")
                    .param("id", savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello keesun"));
    }

    @Test
    public void helloStatic() throws Exception {
        mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Hello Mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    public void stringMessage() throws Exception {
        mockMvc.perform(get("/message")
                    .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2019L);
        person.setName("keesun");

        String jsonString = objectMapper.writeValueAsString(person);

        mockMvc.perform(get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2019))
                .andExpect(jsonPath("$.name").value("keesun"));
    }

    @Test
    public void xmlMessage() throws Exception {
        Person person = new Person();
        person.setId(2019L);
        person.setName("keesun");

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal(person, result);
        String xmlString = stringWriter.toString();

        mockMvc.perform(get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_XML_VALUE)
                    .accept(MediaType.APPLICATION_XML_VALUE)
                    .content(xmlString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/name").string("keesun"))
                .andExpect(xpath("person/id").string("2019"));
    }
}