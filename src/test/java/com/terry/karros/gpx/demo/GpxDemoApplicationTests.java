package com.terry.karros.gpx.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terry.karros.gpx.demo.aspect.LoggingAspect;
import com.terry.karros.gpx.demo.repository.GPXRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = GpxDemoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class GpxDemoApplicationTests {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected GPXRepository repository;

    protected <T> String toJson(T request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(request);
    }

    protected <T> T toObject(String content, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, clazz);
    }
}
