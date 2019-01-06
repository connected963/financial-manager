package com.connected.accountservice.presentation.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class AbstractControllerMockTest {

    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext applicationContext;

    @Before
    public void setup() {
        setupMocks();
        setupObjectMapper();
    }

    private void setupMocks() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    private void setupObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    protected ResultActions performGet(final String url) throws Exception {
        return performGet(url, Map.of());
    }

    protected ResultActions performGet(final String url, final Map<String, Object> parameters) throws Exception {
        final var getBuilder = MockMvcRequestBuilders.get(url);

        parameters.forEach((key, value) -> getBuilder.param(key, value.toString()));
        getBuilder.contentType(MediaType.APPLICATION_JSON);

        return mockMvc.perform(getBuilder);
    }

    protected <T> ResultActions performPost(final String url, final T body) throws Exception {
        final var postBuilder = MockMvcRequestBuilders.post(url);
        postBuilder.content(objectMapper.writeValueAsString(body));
        postBuilder.contentType(MediaType.APPLICATION_JSON);

        return mockMvc.perform(postBuilder);
    }

    protected <T> ResultActions performPut(final String url, final T body) throws Exception {
        final var putBuilder = MockMvcRequestBuilders.put(url);
        putBuilder.content(objectMapper.writeValueAsString(body));
        putBuilder.contentType(MediaType.APPLICATION_JSON);

        return mockMvc.perform(putBuilder);
    }

    protected ResultActions performDelete(final String url) throws Exception {
        return performDelete(url, Map.of());
    }

    protected <T> ResultActions performDelete(final String url, final T body) throws Exception {
        final var deleteBuilder = MockMvcRequestBuilders.delete(url);
        deleteBuilder.content(objectMapper.writeValueAsString(body));
        deleteBuilder.contentType(MediaType.APPLICATION_JSON);

        return mockMvc.perform(deleteBuilder);
    }

}
