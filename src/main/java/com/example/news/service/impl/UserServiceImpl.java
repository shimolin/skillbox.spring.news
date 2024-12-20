package com.example.news.service.impl;

import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.Role;
import com.example.news.model.RoleType;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import com.example.news.web.model.PageFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll(PageFilter filter) {
        if (filter.getPageSize() == null) filter.setPageSize(1000);
        if (filter.getPageNumber() == null) filter.setPageNumber(0);

        return userRepository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("User with id {0} not found!", id))
        );
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("username not found!"));
    }

    @Override
    public User create(User user, Role role) {
        user.setRoles(Collections.singletonList(role));
        role.setUser(user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        existedUser.setFirstName(user.getFirstName());
        existedUser.setLastName(user.getLastName());
        existedUser.setBirthday(user.getBirthday());
        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
