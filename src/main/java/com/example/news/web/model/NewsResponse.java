package com.example.news.web.model;

import com.example.news.model.NewsCategory;
import com.example.news.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private Long id;
    private String title;
    private String body;
    private Instant createdAt;
    private Instant updatedAt;
    private Long categoryId;
    private Long authorId;

}
