package com.example.news.web.controller;

import com.example.news.aop.AuthorCheck;
import com.example.news.aop.SecurityCheck;
import com.example.news.mapper.v2.NewsMapperV2;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final CommentService commentService;
    private final NewsMapperV2 newsMapper;

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    public ResponseEntity<List<NewsResponse>> filterBy(NewsFilter filter) {
        return ResponseEntity.ok(
                newsService.filterBy(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    public ResponseEntity<List<NewsResponse>> findAll(PageFilter filter) {
        return ResponseEntity.ok(
                newsService.findAll(filter).stream()
                        .map(newsMapper::newsToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    public ResponseEntity<OneNewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToOneNewsResponse(
                        newsService.findById(id)
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsResponse> create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid NewsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(
                        newsService.create(
                                newsMapper.requestToNews(request), userDetails
                        )
                ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @AuthorCheck
    public ResponseEntity<NewsResponse> update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody @Valid NewsRequest newsRequest) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.update(
                                newsMapper.requestToNews(newsRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @SecurityCheck
    public void delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        newsService.deleteById(id);
    }


}
