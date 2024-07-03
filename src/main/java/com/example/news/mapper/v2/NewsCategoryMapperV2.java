package com.example.news.mapper.v2;

import com.example.news.model.NewsCategory;
import com.example.news.web.model.NewsCategoryRequest;
import com.example.news.web.model.NewsCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsCategoryMapperV2 {
    NewsCategory requestToNewsCategory(NewsCategoryRequest request);
    NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory);
}
