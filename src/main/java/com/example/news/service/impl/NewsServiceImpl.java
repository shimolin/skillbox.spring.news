package com.example.news.service.impl;

import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

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
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());
        if (existedNews == null){
            throw new EntityNotFoundException(MessageFormat.format("News with id {0} not found!", news.getId()));
        }
        existedNews.setUpdatedAt(Instant.now());
        existedNews.setTitle(news.getTitle());
        existedNews.setBody(news.getBody());
        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
