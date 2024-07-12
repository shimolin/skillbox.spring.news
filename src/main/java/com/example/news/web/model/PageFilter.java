package com.example.news.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageFilter {
    private Integer pageSize;
    private Integer pageNumber;
}
