package com.example.news.service;

import com.example.news.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(Long id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);

    List<Comment> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
