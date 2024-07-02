package com.example.news.mapper.v1;

import com.example.news.model.News;
import com.example.news.service.NewsCategoryService;
import com.example.news.service.UserService;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final UserService userService;
    private final NewsCategoryService newsCategoryService;

    public News requestToNews(NewsRequest request) {
        News news = new News();
        if(request.getTitle() != null){
            news.setTitle(request.getTitle());
        }
        if(request.getBody() != null){
            news.setBody(request.getBody());
        }
        if(request.getCategoryId() != null){
            news.setCategory(newsCategoryService.findById(request.getCategoryId()));
        }
        return news;
    }

    public NewsResponse newsToResponse(News news) {
        if(news == null){
            return null;
        }
        NewsResponse response = new NewsResponse();
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setBody(news.getBody());
        response.setCreatedAt(news.getCreatedAt());
        response.setUpdatedAt(news.getUpdatedAt());
        response.setCategoryId(news.getCategory().getId());
        response.setUserId(news.getUser().getId());
        return response;
    }

}
