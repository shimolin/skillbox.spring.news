package com.example.news.service;

import com.example.news.model.News;
import com.example.news.web.model.NewsFilter;
import com.example.news.web.model.PageFilter;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface NewsService {

    List<News> filterBy(NewsFilter filter);
    List<News> findAll(PageFilter filter);

    News findById(Long id);

    News create(News news, UserDetails userDetails);

    News update(News news);

    void deleteById(Long id);

}
