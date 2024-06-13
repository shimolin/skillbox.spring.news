package com.example.news.mapper.v1;

import com.example.news.model.News;
import com.example.news.model.NewsCategory;
import com.example.news.web.model.NewsCategoryRequest;
import com.example.news.web.model.NewsCategoryResponse;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsCategoryMapper {
    public NewsCategory requestToNewsCategory(NewsCategoryRequest request){
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setName(request.getName());
        return newsCategory;
    }

    public NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory){
        NewsCategoryResponse response = new NewsCategoryResponse();
        response.setId(newsCategory.getId());
        response.setName(newsCategory.getName());
        return response;
    }


}
