package com.example.news.mapper.v2;

import com.example.news.model.Comment;
import com.example.news.web.model.CommentRequest;
import com.example.news.web.model.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperV2 {
    @Mapping(source = "newsId", target = "news.id")
    Comment requestToComment(CommentRequest request);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "news.id", target = "newsId")
 CommentResponse commentToResponse(Comment comment);
}
