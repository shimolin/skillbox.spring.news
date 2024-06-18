package com.example.news.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfiguration {

    @Value("${app.currentUserId}")
    public long currentUserId;

}
