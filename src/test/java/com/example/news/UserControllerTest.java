package com.example.news;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "ivanov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_ADMIN() throws Exception {

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/5"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\": \"aaa\",\n" +
                                "    \"password\": \"555\",\n" +
                                "    \"firstName\": \"aaa\",\n" +
                                "    \"lastName\": \"aaa\",\n" +
                                "    \"birthday\": \"2000-01-01\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/user/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"bbbb\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/user/6"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "petrov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_MODERATOR() throws Exception {

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/user/2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/5"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\": \"aaa\",\n" +
                                "    \"password\": \"555\",\n" +
                                "    \"firstName\": \"aaa\",\n" +
                                "    \"lastName\": \"aaa\",\n" +
                                "    \"birthday\": \"2000-01-01\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/user/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"bbbb\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/user/6"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "sidorov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_USER() throws Exception {

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/user/3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/5"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\": \"aaa\",\n" +
                                "    \"password\": \"555\",\n" +
                                "    \"firstName\": \"aaa\",\n" +
                                "    \"lastName\": \"aaa\",\n" +
                                "    \"birthday\": \"2000-01-01\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/user/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Sidoroff\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/user/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"bbbb\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/api/user/8"))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/api/user/3"))
                .andExpect(status().isOk());

    }

}
