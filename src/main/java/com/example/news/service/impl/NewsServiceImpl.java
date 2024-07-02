package com.example.news.service.impl;

import com.example.news.exception.EntityNotFoundException;
import com.example.news.aop.AuthorCheck;
import com.example.news.configuration.AppConfiguration;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;
    private final AppConfiguration appConfiguration;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("News with id {0} not found!", id))
        );
    }

    @Override
    public News create(News news) {
        news.setCreatedAt(Instant.now());
        news.setUpdatedAt(Instant.now());
        news.setUser(userService.findById(appConfiguration.currentUserId));
        return newsRepository.save(news);
    }

    @Override
    @AuthorCheck
    public News update(News news) {
        News existedNews = findById(news.getId());
        if (existedNews == null){
            throw new EntityNotFoundException(MessageFormat.format("News with id {0} not found!", news.getId()));
        }
        existedNews.setUpdatedAt(Instant.now());
        existedNews.setTitle(news.getTitle());
        existedNews.setBody(news.getBody());
        existedNews.setCategory(news.getCategory());
        return newsRepository.save(existedNews);
    }

    @Override
    @AuthorCheck
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }


    @Override
    public List<News> findNewsByUserId(Long id) {
        List<News> news;
        news = newsRepository.findNewsByUserId(id);
        return news;
    }
}
