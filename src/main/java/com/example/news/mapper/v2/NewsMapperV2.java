package com.example.news.mapper.v2;

import com.example.news.model.News;
import com.example.news.service.CommentService;
import com.example.news.service.impl.CommentServiceImpl;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapperV2.class})
public interface NewsMapperV2 {

    @Mapping(source = "categoryId", target = "category.id")
    News requestToNews(NewsRequest request);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    NewsResponse newsToResponse(News news);
}
