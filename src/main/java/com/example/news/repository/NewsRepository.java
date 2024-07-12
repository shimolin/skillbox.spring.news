package com.example.news.repository;

import com.example.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByUserId(Long id);
    List<News> findAllByCategoryId(Long id);
    List<News> findAllByUserIdAndCategoryId(Long userId, Long categoryId);
}
