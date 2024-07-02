package com.example.news.exception;

public class NotPermitException extends RuntimeException{
    public NotPermitException(String message) {
        super(message);
    }
}
