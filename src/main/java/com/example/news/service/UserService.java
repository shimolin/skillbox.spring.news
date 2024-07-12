package com.example.news.service;
import com.example.news.model.User;
import com.example.news.web.model.PageFilter;

import java.util.List;

public interface UserService {
    List<User> findAll(PageFilter filter);

    User findById(Long id);

    User create(User user);

    User update(User user);

    void deleteById(Long id);

}
