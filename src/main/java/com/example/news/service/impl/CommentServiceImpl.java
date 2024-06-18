package com.example.news.service.impl;

import com.example.news.aop.AuthorCheck;
import com.example.news.model.Comment;
import com.example.news.repository.CommentRepository;
import com.example.news.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

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
    public Comment create(Comment comment) {
        comment.setCreatedAt(Instant.now());
        comment.setUpdatedAt(Instant.now());
        return commentRepository.save(comment);
    }

    @Override
    @AuthorCheck
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        if (existedComment == null) {
            throw new EntityNotFoundException(MessageFormat.format("Comment with id {0} not found!", comment.getId()));
        }
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
