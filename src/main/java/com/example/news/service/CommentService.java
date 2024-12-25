package com.example.news.service;

import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.web.model.CommentFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(Long id);

    Comment create(Comment comment, UserDetails userDetails);

    Comment update(Comment comment);

    void deleteById(Long id);

    List<Comment> findByNewsId(CommentFilter filter);

    Integer getCommentCountByNewsId(Long newsId);

}
