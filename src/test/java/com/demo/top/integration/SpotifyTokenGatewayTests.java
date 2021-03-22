package com.demo.top.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.demo.top.model.token.Token;
import com.demo.top.spotify.SpotifyTokenGateway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpotifyTokenGatewayTests {

  @Autowired
  private SpotifyTokenGateway spotifyTokenGateway;

  @MockBean
  private RestTemplate restTemplate;

  @BeforeEach
  void setup() {
    initMocks(this);
  }

  @Test
  void givenToken_whenGetToken_thenReturnsCorrectToken() {

    when(restTemplate.postForObject(anyString(),any(HttpEntity.class),eq(Token.class))).thenReturn(getToken());

    Token token = spotifyTokenGateway.getToken();

    assertThat(token).isEqualToComparingFieldByField(getToken());
  }

  public Token getToken() {
    return new Token("1234","1234","1234","1234");
  }





}
