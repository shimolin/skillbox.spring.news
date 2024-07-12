package com.example.news.repository;

import com.example.news.model.Comment;
import com.example.news.model.News;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByUserId(Long id);

    List<Comment> findByNewsId(Long newsId);

    void deleteCommentByUserId(Long userId);

    @Query("SELECT count(*) FROM com.example.news.model.Comment WHERE news.id= :id")
    Integer getCommentsCountByNewsId(Long id);

}
