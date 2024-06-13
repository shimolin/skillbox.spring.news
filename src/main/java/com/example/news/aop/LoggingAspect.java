package com.example.news.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Pointcut("execution(public void com.example.news.loaddata.LoadDataOnStart.loadData())")
//    public void callAtLoadData(){}

//    @Before("callAtLoadData()")
    @Before("@annotation(Loggable)")
    public void logBefore(JoinPoint joinPoint){
        log.info("---Before execute of: {}", joinPoint.getSignature().getName());
    }
//    @After("callAtLoadData()")
    @After("@annotation(Loggable)")
    public void logAfter(JoinPoint joinPoint){
        log.info("---After execute of: {}", joinPoint.getSignature().getName());
    }
}
