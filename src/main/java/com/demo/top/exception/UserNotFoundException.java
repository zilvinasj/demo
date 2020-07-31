package com.demo.top.exception;

public class UserNotFoundException extends NotFoundException {
    private Long userId;

    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
