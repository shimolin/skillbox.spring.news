package com.example.news.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneNewsResponse {
    private Long id;
    private String title;
    private String body;
    private Instant createdAt;
    private Instant updatedAt;
    private Long categoryId;
    private Long userId;
    private List<CommentResponse> comments;
}
