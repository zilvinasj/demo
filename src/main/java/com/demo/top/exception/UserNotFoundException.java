package com.demo.top.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private Long userId;

    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }
}
