package com.example.news.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

    @NotBlank(message = "Body must be not blank!")
    private String body;

    @NotNull(message = "NewsId must be not null!")
    private Long newsId;

}
