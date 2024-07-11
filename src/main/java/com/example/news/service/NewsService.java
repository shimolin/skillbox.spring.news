package com.example.news.service;

import com.example.news.model.News;
import com.example.news.web.model.NewsFilter;
import org.springframework.boot.web.servlet.filter.OrderedFilter;

import java.util.List;

public interface NewsService {

    List<News> filterBy(NewsFilter filter);
    List<News> findAll();

    News findById(Long id);

    News create(News news);

    News update(News news);

    void deleteById(Long id);

}
