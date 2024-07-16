package com.example.news.mapper.v2;

import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.web.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapperV2.class})
public interface NewsMapperV2 {

    @Mapping(source = "categoryId", target = "category.id")
    News requestToNews(NewsRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    NewsResponse newsToResponse(News news);

//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "category.id", target = "categoryId")
//    NewsResponseById newsToResponseById(News news);

    default public OneNewsResponse newsToOneNewsResponse(News news) {
        if ( news == null ) {
            return null;
        }

        OneNewsResponse newsResponseById = new OneNewsResponse();
        CommentMapperV2 commentMapperV2 = new CommentMapperV2Impl();

        newsResponseById.setUserId( news.getUser().getId() );
        newsResponseById.setCategoryId( news.getCategory().getId());
        newsResponseById.setId( news.getId() );
        newsResponseById.setTitle( news.getTitle() );
        newsResponseById.setBody( news.getBody() );
        newsResponseById.setCreatedAt( news.getCreatedAt() );
        newsResponseById.setUpdatedAt( news.getUpdatedAt() );

        List<CommentResponse>  commentResponseList =  new ArrayList<>();
        for(Comment comment: news.getComments()){
            commentResponseList.add(commentMapperV2.commentToResponse(comment));
        }
        newsResponseById.setComments(commentResponseList);

        return newsResponseById;
    }


}
