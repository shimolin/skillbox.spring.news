package com.example.news.repository;

import com.example.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findNewsByUserId(Long id);
    void deleteNewsByUserId(Long id);
}
