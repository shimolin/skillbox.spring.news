package com.example.news.web.controller;

import com.example.news.mapper.v2.NewsCategoryMapperV2;
import com.example.news.service.NewsCategoryService;
import com.example.news.web.model.NewsCategoryRequest;
import com.example.news.web.model.NewsCategoryResponse;
import com.example.news.web.model.PageFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/newscategory")
@RequiredArgsConstructor
public class NewsCategoryController {
    private final NewsCategoryService newsCategoryService;
    private final NewsCategoryMapperV2 newsCategoryMapper;

    @GetMapping
    public ResponseEntity<List<NewsCategoryResponse>> findAll(PageFilter filter) {
        return ResponseEntity.ok(
                newsCategoryService.findAll(filter).stream()
                        .map(newsCategoryMapper::newsCategoryToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsCategoryMapper.newsCategoryToResponse(newsCategoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsCategoryResponse> create(@RequestBody @Valid NewsCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsCategoryMapper.newsCategoryToResponse(
                        newsCategoryService.create(
                                newsCategoryMapper.requestToNewsCategory(request)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable Long id, @RequestBody @Valid NewsCategoryRequest newsCategoryRequest){
        return ResponseEntity.ok(
                newsCategoryMapper.newsCategoryToResponse(
                        newsCategoryService.update(
                                newsCategoryMapper.requestToNewsCategory(newsCategoryRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        newsCategoryService.deleteById(id);
    }
}
