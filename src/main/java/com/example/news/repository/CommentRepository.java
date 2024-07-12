package com.example.news.repository;

import com.example.news.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByUserId(Long id);

    List<Comment> findByNewsId(Long newsId);

    void deleteCommentByUserId(Long userId);


}
