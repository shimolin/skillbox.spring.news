package com.example.news.web.controller;

import com.example.news.mapper.v1.CommentMapper;
import com.example.news.mapper.v2.CommentMapperV2;
import com.example.news.service.CommentService;
import com.example.news.web.model.CommentRequest;
import com.example.news.web.model.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV2 commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> findAll() {
        return ResponseEntity.ok(
                commentService.findAll().stream()
                        .map(commentMapper::commentToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(
                        commentService.create(
                                commentMapper.requestToComment(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody @Valid CommentRequest request) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.update(
                                commentMapper.requestToComment(request).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
