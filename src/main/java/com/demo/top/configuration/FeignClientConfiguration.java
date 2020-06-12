package com.demo.top.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    private final ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_ABSENT);

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(objectMapper);
    }

}