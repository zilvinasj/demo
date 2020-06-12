package com.demo.top.exception;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerTests {

    private final TopExceptionHandler handler = new TopExceptionHandler();

    @Test
    public void testFeignException() {
        ResponseEntity responseEntity = handler.handle(Mockito.mock(FeignException.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, responseEntity.getStatusCode());
    }

    @Test
    public void testAppAccessException() {
        ResponseEntity responseEntity = handler.handle(Mockito.mock(UserNotFoundException.class));
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testInternalException() {
        ResponseEntity responseEntity = handler.handleInternal(Mockito.mock(Exception.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testParameterException() {
        ResponseEntity responseEntity = handler.handle(Mockito.mock(MissingServletRequestParameterException.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
