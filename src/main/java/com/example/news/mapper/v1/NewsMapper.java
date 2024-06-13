package com.example.news.mapper.v1;

import com.example.news.model.News;
import com.example.news.service.UserService;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final UserService userService;

    public News requestToNews(NewsRequest request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setBody(request.getBody());
        return news;

    }

    public NewsResponse newsToResponse(News news) {
        NewsResponse response = new NewsResponse();
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setBody(news.getBody());
        response.setUserId(news.getUser().getId());
        response.setCreatedAt(news.getCreatedAt());
        response.setCategoryId(news.getCategory().getId());
        return response;
    }

}
