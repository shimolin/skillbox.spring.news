package com.example.news.mapper.v2;

import com.example.news.model.News;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapperV2 {
    News requestToNews(NewsRequest request);
    NewsResponse newsToResponse(News news);
}
