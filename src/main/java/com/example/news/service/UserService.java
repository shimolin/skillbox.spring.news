package com.example.news.service;
import com.example.news.model.Role;
import com.example.news.model.RoleType;
import com.example.news.model.User;
import com.example.news.web.model.PageFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> findAll(PageFilter filter);

    User findById(Long id);

    User findByUsername(String username);

    User create(User user, Role role);

    User update(User user);

    void deleteById(Long id);

}
