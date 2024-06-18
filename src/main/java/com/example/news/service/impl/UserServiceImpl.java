package com.example.news.service.impl;

import com.example.news.aop.Loggable;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("User with id {0} not found!", id))
        );
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        if(existedUser == null){
            throw new EntityNotFoundException(MessageFormat.format("User with id {0} not found!", user.getId()));
        }
        existedUser.setFirstName(user.getFirstName());
        existedUser.setLastName(user.getLastName());
        existedUser.setBirthday(user.getBirthday());
        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
        //todo не удалять user если у него есть news и/или comments
        // или удалять news, comments а потом user
        userRepository.deleteById(id);
    }
}
