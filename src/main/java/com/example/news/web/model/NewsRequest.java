package com.example.news.web.model;

import com.example.news.model.News;
import com.example.news.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {

    private String title;

    private String body;

    private Long categoryId;

    private Long userId;

}
