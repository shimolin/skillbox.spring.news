package com.example.news.web.controller;

import com.example.news.aop.Loggable;
import com.example.news.exception.EntityNotFoundException;
import com.example.news.mapper.v1.UserMapper;
import com.example.news.mapper.v2.UserMapperV2;
import com.example.news.model.Role;
import com.example.news.model.RoleType;
import com.example.news.service.UserService;
import com.example.news.web.model.PageFilter;
import com.example.news.web.model.UserRequest;
import com.example.news.web.model.UserResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapperV2 userMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<List<UserResponse>> findAll(PageFilter filter){
        return ResponseEntity.ok(
                userService.findAll(filter).stream()
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
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.create(
                                userMapper.requestToUser(userRequest), Role.from(RoleType.ROLE_USER)
                        )
                ));
    }


    @PostMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest, @RequestParam RoleType roleType){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.create(
                                userMapper.requestToUser(userRequest), Role.from(roleType)
                        )
                ));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest){
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
