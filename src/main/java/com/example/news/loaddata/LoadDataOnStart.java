package com.example.news.loaddata;

import com.example.news.aop.Loggable;
import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.model.NewsCategory;
import com.example.news.model.User;
import com.example.news.service.CommentService;
import com.example.news.service.NewsCategoryService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
@ConditionalOnProperty("app.load-data-on-start.enabled")
@RequiredArgsConstructor
public class LoadDataOnStart {
    private final UserService userService;
    private final NewsCategoryService newsCategoryService;
    private final NewsService newsService;
    private final CommentService commentService;

    @Value("${app.load-data-on-start.data-filename}")
    private String filename;


//    @PostConstruct
    @Loggable
    @EventListener(ApplicationStartedEvent.class)
    public void loadData(){
        JSONParser parser = new JSONParser();
        try {
            JSONObject data = (JSONObject) parser.parse(getJsonFile());

            JSONArray users = (JSONArray) data.get("users");
            for (Object user : users) {
                JSONObject o = (JSONObject) user;
                Long id = (Long) o.get("id");
                String firstName = (String) o.get("first_name");
                String lastName = (String) o.get("last_name");
                LocalDate birthdate = LocalDate.parse((String) o.get("birthday"));
                userService.create(new User(id, firstName, lastName, birthdate, null, null));
            }

            JSONArray news_categories = (JSONArray) data.get("news_categories");
            for(Object news_category : news_categories){
                JSONObject o = (JSONObject) news_category;
                Long id = (Long) o.get("id");
                String name = (String) o.get("name");
                newsCategoryService.create(new NewsCategory(id, name,null));
            }

            JSONArray news = (JSONArray) data.get("news");
            for(Object n : news){
                JSONObject o = (JSONObject) n;
                Long id = (Long) o.get("id");
                String body = (String) o.get("body");
                String title = (String) o.get("title");
                Instant createdAt = Instant.parse((CharSequence) o.get("created_at"));//  "2024-06-06T04:33:44.471Z",
                Instant updatedAt = Instant.parse((CharSequence) o.get("updated_at"));//  "2024-06-06T04:33:44.471Z",
                Long userId = (Long) o.get("user_id");
                Long categoryId = (Long) o.get("category_id");
                newsService.create(new News(id,title, body, createdAt, updatedAt, newsCategoryService.findById(categoryId), userService.findById(userId), null, null));
            }

            JSONArray comments = (JSONArray) data.get("comments");
            for(Object comment : comments){
                JSONObject o = (JSONObject) comment;
                Long id = (Long) o.get("id");
                String body = (String) o.get("body");
                Instant createdAt = Instant.parse((CharSequence) o.get("created_at"));//  "2024-06-06T04:33:44.471Z",
                Instant updatedAt = Instant.parse((CharSequence) o.get("updated_at"));//  "2024-06-06T04:33:44.471Z",
                Long userId = (Long) o.get("user_id");
                Long newsId = (Long) o.get("news_id");
                commentService.create(new Comment(id,body,createdAt,updatedAt,userService.findById(userId), newsService.findById(newsId)));
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    private  String getJsonFile(){
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.forEach(builder::append);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return builder.toString();
    }


}
