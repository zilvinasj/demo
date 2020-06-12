package com.demo.top.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_ABSENT);

    public static <TYPE> TYPE getStringAsObject(String content, Class<TYPE> responseType) throws JsonProcessingException {
        return objectMapper.readValue(content, responseType);
    }

    public static String getObjectAsString(Object content) throws JsonProcessingException {
        return objectMapper.writeValueAsString(content);
    }
}
