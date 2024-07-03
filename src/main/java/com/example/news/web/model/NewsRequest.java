package com.example.news.web.model;

import com.example.news.model.News;
import com.example.news.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {

    @NotBlank(message = "Title must be not blank!")
    private String title;

    @NotBlank(message = "Title must be not blank!")
    private String body;

    @NotNull(message = "CategoryId must be not null!")
    private Long categoryId;
}
