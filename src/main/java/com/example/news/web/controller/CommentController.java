package com.example.news.web.controller;

import com.example.news.aop.AuthorCheck;
import com.example.news.aop.SecurityCheck;
import com.example.news.mapper.v1.CommentMapper;
import com.example.news.mapper.v2.CommentMapperV2;
import com.example.news.service.CommentService;
import com.example.news.web.model.CommentFilter;
import com.example.news.web.model.CommentRequest;
import com.example.news.web.model.CommentResponse;
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
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV2 commentMapper;

//    @GetMapping
//    public ResponseEntity<List<CommentResponse>> findAll() {
//        return ResponseEntity.ok(
//                commentService.findAll().stream()
//                        .map(commentMapper::commentToResponse)
//                        .collect(Collectors.toList()));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(
//                commentMapper.commentToResponse(
//                        commentService.findById(id)
//                )
//        );
//    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")

    public ResponseEntity<List<CommentResponse>> findByNewsId(CommentFilter filter){
        return ResponseEntity.ok(commentService.findByNewsId(filter)
                .stream()
                .map(commentMapper::commentToResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    public ResponseEntity<CommentResponse> create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(
                        commentService.create(
                                commentMapper.requestToComment(request), userDetails
                        )
                ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @AuthorCheck
    public ResponseEntity<CommentResponse> update(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id, @RequestBody @Valid CommentRequest request) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.update(
                                commentMapper.requestToComment(request).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @SecurityCheck
    public void deleteById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        commentService.deleteById(id);
    }
}
