package com.example.news;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NewsControllerTest extends AbstractControllerTest{

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "ivanov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_ADMIN() throws Exception {

        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String res = mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\": \"News 111\",\n" +
                                "    \"body\": \"111\",\n" +
                                "    \"categoryId\": \"2\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/news/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isForbidden());

        res = mockMvc.perform(put("/api/news/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(delete("/api/news/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/news/3"))
                .andExpect(status().isOk());
    }


    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "petrov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_MODEATOR() throws Exception {

        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String res = mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\": \"News 111\",\n" +
                                "    \"body\": \"111\",\n" +
                                "    \"categoryId\": \"2\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/news/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isForbidden());

        res = mockMvc.perform(put("/api/news/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(delete("/api/news/2"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/news/5"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "sidorov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_USER() throws Exception {

        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String res = mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\": \"News 111\",\n" +
                                "    \"body\": \"111\",\n" +
                                "    \"categoryId\": \"2\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/news/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isForbidden());

        res = mockMvc.perform(put("/api/news/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(res);

        mockMvc.perform(delete("/api/news/3"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/news/5"))
                .andExpect(status().isForbidden());
    }

}
