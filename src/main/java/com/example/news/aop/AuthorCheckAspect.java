package com.example.news.aop;

import com.example.news.exception.ForbiddenException;
import com.example.news.exception.NotPermitException;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorCheckAspect {

    private final UserService userService;
    private final NewsService newsService;
    private final CommentService commentService;

    @Around("@annotation(AuthorCheck) && args(userDetails, requestedUserId, ..)")
    public Object authorCheck(ProceedingJoinPoint pjp, UserDetails userDetails, Long requestedUserId) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVar = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long pathVarId = Long.valueOf(pathVar.get("id"));

        Long currentUserId = userService.findByUsername(userDetails.getUsername()).getId();

        Long authorId = switch (pjp.getSignature().getDeclaringTypeName()) {

            case "com.example.news.web.controller.NewsController",
                    "com.example.news.service.impl.NewsServiceImpl" ->
                    newsService.findById(pathVarId).getUser().getId();

            case "com.example.news.web.controller.CommentController",
                    "com.example.news.service.impl.CommentServiceImpl" ->
                    commentService.findById(pathVarId).getUser().getId();

            default -> null;
        };

        if (!currentUserId.equals(authorId)) {
            throw new ForbiddenException("Forbidden!!!");
        }

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }


}
