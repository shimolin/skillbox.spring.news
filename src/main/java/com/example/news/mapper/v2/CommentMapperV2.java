package com.example.news.mapper.v2;

import com.example.news.model.Comment;
import com.example.news.web.model.CommentRequest;
import com.example.news.web.model.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperV2 {
    Comment requestToComment(CommentRequest request);
    CommentResponse commentToResponse(Comment comment);
}
