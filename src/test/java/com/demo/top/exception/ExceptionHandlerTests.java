package com.demo.top.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

class ExceptionHandlerTests {

    private final TopExceptionHandler handler = new TopExceptionHandler();

    @Test
    void testInternalException() {
        ResponseEntity<Void> responseEntity = handler.handleInternal(Mockito.mock(Exception.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testFailedDependencyException() {
        ResponseEntity<ErrorBody> responseEntity = handler.handle(Mockito.mock(FailedDependencyException.class));
        assertEquals(HttpStatus.FAILED_DEPENDENCY, responseEntity.getStatusCode());
    }

    @Test
    void testParameterException() {
        ResponseEntity<ErrorBody> responseEntity = handler.handle(Mockito.mock(MissingServletRequestParameterException.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
