package com.example.news.web.controller;

import com.example.news.aop.Loggable;
import com.example.news.exception.EntityNotFoundException;
import com.example.news.mapper.v1.UserMapper;
import com.example.news.service.UserService;
import com.example.news.web.model.UserRequest;
import com.example.news.web.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(
                userService.findAll().stream()
                        .map(userMapper::userToResponse)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)
                )
        );
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.create(
                                userMapper.requestToUser(userRequest)
                        )
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.update(
                                userMapper.requestToUser(userRequest).setId(id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

}
