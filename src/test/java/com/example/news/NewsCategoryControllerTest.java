package com.example.news;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NewsCategoryControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "ivanov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_ADMIN() throws Exception {

        mockMvc.perform(get("/api/newscategory"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/newscategory/1"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/newscategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Game\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/newscategory/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Games\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/newscategory/3"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "petrov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_MODERATOR() throws Exception {

        mockMvc.perform(get("/api/newscategory"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/newscategory/1"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/newscategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Game\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/newscategory/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Games\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/newscategory/3"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "sidorov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_USER() throws Exception {

        mockMvc.perform(get("/api/newscategory"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/newscategory/1"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/newscategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Game\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(put("/api/newscategory/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Games\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/api/newscategory/3"))
                .andExpect(status().isForbidden());
    }


}
