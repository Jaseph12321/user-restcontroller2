package com.example.controller.response;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message) {
        super(message);
    }
}
