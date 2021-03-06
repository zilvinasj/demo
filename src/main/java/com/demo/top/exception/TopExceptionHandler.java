package com.demo.top.exception;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TopExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TopExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handle(MissingServletRequestParameterException e) {
        log.error("User did not supply a parameter: {}", e.getParameterName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorBody(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handle(FailedDependencyException e) {
        log.error("There is a problem with the Spotify gateway: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ErrorBody(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handle(MethodArgumentNotValidException e) {
        log.error("User did not submit the request correctly: {}", e.getMessage());
        String errorMessage = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(","));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorBody(errorMessage));
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleInternal(Exception e) {
        log.error("Internal exception occurred: {}, with message: {}", e, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
