package com.demo.top.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TopExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TopExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Void> handle(FeignException e) {
        log.error("Exception when communicating with Itunes: {}, cause: {}", e, e.getCause());
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handle(UserNotFoundException e) {
        log.error("Exception when trying to find user with id: {}", e.getUserId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handle(MissingServletRequestParameterException e) {
        log.error("User did not supply a parameter: {}", e.getParameterName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorBody(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleInternal(Exception e) {
        log.error("Internal exception occurred: {}, with message: {}", e, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
