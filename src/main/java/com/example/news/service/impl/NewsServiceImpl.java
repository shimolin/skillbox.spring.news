package com.example.news.service.impl;

import com.example.news.aop.AuthorCheck;
import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.web.model.NewsFilter;
import com.example.news.web.model.PageFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;
    private final CommentService commentService;

    @Override
    public List<News> filterBy(NewsFilter filter) {
        List<News> newsList;
        if (filter.getUserId() == null && filter.getCategoryId() != null) {
            newsList = newsRepository.findAllByCategoryId(filter.getCategoryId());
        } else if (filter.getUserId() != null && filter.getCategoryId() == null) {
            newsList = newsRepository.findAllByUserId(filter.getUserId());
        } else {
            newsList = newsRepository.findAllByUserIdAndCategoryId(filter.getUserId(), filter.getCategoryId());
        }

        for (News n : newsList) {
            n.setCommentCount(commentService.getCommentCountByNewsId(n.getId()));
        }
        return newsList;
    }

    @Override
    public List<News> findAll(PageFilter filter) {
        if (filter.getPageSize() == null) filter.setPageSize(1000);
        if (filter.getPageNumber() == null) filter.setPageNumber(0);

        List<News> newsList = newsRepository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();

        for (News n : newsList) {
            n.setCommentCount(commentService.getCommentCountByNewsId(n.getId()));
        }
        return newsList;
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
        //TODO
//        news.setUser(userService.findById(appConfiguration.currentUserId));
        return newsRepository.save(news);
    }

    @Override
    @AuthorCheck
    public News update(News news) {
        News existedNews = findById(news.getId());
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

}
