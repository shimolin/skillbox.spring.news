package com.example.news.mapper.v1;

import com.example.news.model.Comment;
import com.example.news.web.model.CommentRequest;
import com.example.news.web.model.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment requestToComment(CommentRequest request){
        Comment comment = new Comment();
        if (request.getBody() != null) {
            comment.setBody(request.getBody());
        }
        return comment;
    }

    public CommentResponse commentToResponse(Comment comment){
        if(comment == null){
            return null;
        }
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setBody(comment.getBody());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        response.setAuthorId(comment.getAuthor().getId());
        response.setNewsId(comment.getNews().getId());
        return response;

    }
}
