package com.example.news.service.impl;

import com.example.news.aop.AuthorCheck;
import com.example.news.configuration.AppConfiguration;
import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.repository.CommentRepository;
import com.example.news.service.CommentService;
import com.example.news.exception.EntityNotFoundException;
import com.example.news.service.UserService;
import com.example.news.web.model.CommentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AppConfiguration appConfiguration;


    @Override
    public Integer getCommentCountByNewsId(Long newsId) {
        return commentRepository.getCommentsCountByNewsId(newsId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Comment with id {0} not found!", id))
        );
    }

    @Override
    public List<Comment> findByNewsId(CommentFilter filter) {
        return commentRepository.findByNewsId(filter.getNewsId());
    }

    @Override
    public Comment create(Comment comment) {
        comment.setCreatedAt(Instant.now());
        comment.setUpdatedAt(Instant.now());
        comment.setUser(userService.findById(appConfiguration.currentUserId));
        return commentRepository.save(comment);
    }

    @Override
    @AuthorCheck
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
//        if (existedComment == null) {
//            throw new EntityNotFoundException(MessageFormat.format("Comment with id {0} not found!", comment.getId()));
//        }
        existedComment.setBody(comment.getBody());
        existedComment.setUpdatedAt(Instant.now());
        return commentRepository.save(existedComment);
    }

    @Override
    @AuthorCheck
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
