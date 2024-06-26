package com.example.news.web.controller;

import com.example.news.mapper.v1.NewsMapper;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import com.example.news.web.model.NewsRequest;
import com.example.news.web.model.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<List<NewsResponse>> findAll() {
        return ResponseEntity.ok(
                newsService.findAll().stream()
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
    public ResponseEntity<NewsResponse> create(@RequestBody NewsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(
                        newsService.create(
                                newsMapper.requestToNews(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody NewsRequest newsRequest){
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.update(
                                newsMapper.requestToNews(newsRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        newsService.deleteById(id);
    }


}
