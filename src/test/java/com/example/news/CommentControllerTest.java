package com.example.news;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest extends AbstractControllerTest{

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "userDetailServiceImpl",
            value = "ivanov", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void testUserWithROLE_ADMIN() throws Exception {

//        mockMvc.perform(get("/api/comment"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        String res = mockMvc.perform(get("/api/comment/6"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        System.out.println(res);

        mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"body\": \"111\",\n" +
                                "    \"newsId\": \"2\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/comment/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(put("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"body\": \"2222\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(delete("/api/comment/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/comment/3"))
                .andExpect(status().isOk());
    }

}
