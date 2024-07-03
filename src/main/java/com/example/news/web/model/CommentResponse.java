package com.example.news.web.model;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {
    private Long id;
    private String body;
    private Instant createdAt;
    private Instant updatedAt;
    private Long userId;
    private Long newsId;
}
