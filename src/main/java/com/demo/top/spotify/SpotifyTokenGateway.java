package com.demo.top.spotify;

import com.demo.top.model.token.Token;
import java.nio.charset.Charset;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class SpotifyTokenGateway {


  private final String tokenUrl;

  private final String clientId;

  private final String clientSecret;

  private final RestTemplate restTemplate;

  @Autowired
  public SpotifyTokenGateway(
      RestTemplate restTemplate,
      @Value("${top-application.spotify.token-url}") String tokenUrl,
      @Value("${top-application.spotify.client-id}") String clientId,
      @Value("${top-application.spotify.client-secret}") String clientSecret) {
    this.restTemplate = restTemplate;
    this.tokenUrl = tokenUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public Token getToken() {

    HttpHeaders headers = new HttpHeaders();

    String value = clientId + ":" + clientSecret;

    headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(value.getBytes(
        Charset.defaultCharset())));

    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "client_credentials");

    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

    return restTemplate.postForObject(tokenUrl, entity, Token.class);
  }
}
