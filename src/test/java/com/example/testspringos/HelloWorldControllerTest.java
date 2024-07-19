package com.example.testspringos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testspringos.controller.HelloWorldController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(get("/api/v1/helloworld"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World da OpenShift"));
    }

    @Test
    public void testPostMethodName() throws Exception {
        String requestBody = """
            {
                "username": "testuser"
            }
        """;

        String expectedResponse = """
            {
                "result": "ok",
                "username": "testuser"
            }
        """;

        mockMvc.perform(post("/api/v1/testpost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test()
    public void testPostMethodNameMissingUsername() throws Exception {
        String requestBody = """
            {
                "otherField": "value"
            }
        """;

        String expectedErrorResponse = """
            {
                "error": "username is required"
            }
        """;

        mockMvc.perform(post("/api/v1/testpost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedErrorResponse));
    }
}

