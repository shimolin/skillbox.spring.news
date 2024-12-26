package com.example.news;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractTestController {
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getAllWithRoleUser_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllWithRoleAdmin_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(ResultMatcher.matchAll());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "ivanov",
            setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void getByIdWithDBUserRoleAdmin_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/user/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":4," +
                        "\"username\":\"nikolaev\"," +
                        "\"firstName\":\"Nikolay\"," +
                        "\"lastName\":\"Nikolaev\"," +
                        "\"birthday\":\"2003-03-03\"," +
                        "\"roles\":[\"ROLE_USER\"]}"));
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "nikolaev",
            setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void getByIdWithDBUserRoleUser_thenReturnOk() throws Exception {
        mockMvc.perform(get("/api/user/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":4," +
                        "\"username\":\"nikolaev\"," +
                        "\"firstName\":\"Nikolay\"," +
                        "\"lastName\":\"Nikolaev\"," +
                        "\"birthday\":\"2003-03-03\"," +
                        "\"roles\":[\"ROLE_USER\"]}"));
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "nikolaev",
            setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void getByIdWithDBUserRoleUser_thenReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isForbidden());
    }

}
