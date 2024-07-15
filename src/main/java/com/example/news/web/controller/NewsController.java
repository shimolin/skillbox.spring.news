package com.example.news.web.controller;

import com.example.news.mapper.v2.NewsMapperV2;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final CommentService commentService;
    private final NewsMapperV2 newsMapper;

    @GetMapping("/filter")
    public ResponseEntity<List<NewsResponse>> filterBy(NewsFilter filter) {
        return ResponseEntity.ok(
                newsService.filterBy(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> findAll(PageFilter filter) {
        return ResponseEntity.ok(
                newsService.findAll(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(
                        newsService.create(
                                newsMapper.requestToNews(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody @Valid NewsRequest newsRequest) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.update(
                                newsMapper.requestToNews(newsRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.deleteById(id);
    }


}
