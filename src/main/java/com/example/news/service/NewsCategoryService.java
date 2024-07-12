package com.example.news.service;

import com.example.news.model.News;
import com.example.news.model.NewsCategory;
import com.example.news.web.model.PageFilter;

import java.util.List;

public interface NewsCategoryService {
    List<NewsCategory> findAll(PageFilter filter);

    NewsCategory findById(Long id);

    NewsCategory create(NewsCategory newsCategory);

    NewsCategory update(NewsCategory newsCategory);

    void deleteById(Long id);

}
