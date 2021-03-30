package com.demo.top.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpotifyGatewayConfiguration {

    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
      return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
          .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
          .setDefaultPropertyInclusion(Include.NON_ABSENT);
    }

}
