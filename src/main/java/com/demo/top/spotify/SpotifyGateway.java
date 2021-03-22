package com.demo.top.spotify;

import com.demo.top.exception.FailedDependencyException;
import com.demo.top.exception.InternalServerException;
import com.demo.top.model.spotify.SearchType;
import com.demo.top.model.token.Token;
import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.ArtistSearchResponse;
import com.demo.top.model.track.TrackSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpotifyGateway {

  private final RestTemplate restTemplate;

  private final String spotifyUrl;

  private final ObjectMapper objectMapper;

  private static final String QUERY = "/search?q={q}&type={type}&limit={limit}";

  @Autowired
  public SpotifyGateway(RestTemplate restTemplate,
      ObjectMapper objectMapper,
      @Value("${top-application.spotify.url}") String spotifyUrl) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.spotifyUrl = spotifyUrl;
  }

  public ArtistSearchResponse getArtists(String artistName, Token token) {
    HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(getHeaders(token));

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        spotifyUrl + QUERY, HttpMethod.GET, httpEntity, String.class, artistName, SearchType.ARTIST.getType(), 50);

    return jsonToObject(responseEntity.getBody(), ArtistSearchResponse.class);
  }

  public AlbumSearchResponse getAlbums(String albumName, Token token) {
    HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(getHeaders(token));

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        spotifyUrl + QUERY, HttpMethod.GET, httpEntity, String.class, albumName, SearchType.ALBUM.getType(), 50);

    return jsonToObject(responseEntity.getBody(), AlbumSearchResponse.class);
  }

  public TrackSearchResponse getTracks(String songName, Token token) {
    HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(getHeaders(token));

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        spotifyUrl + QUERY, HttpMethod.GET, httpEntity, String.class, songName,
        SearchType.TRACK.getType(), 50);

    return jsonToObject(responseEntity.getBody(), TrackSearchResponse.class);
  }

  private HttpHeaders getHeaders(Token token) {
    HttpHeaders headers = new HttpHeaders();

    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken() );

    return headers;
  }

  private <T> T jsonToObject(String json, Class<T> clazz) {
    if (json == null || json.length() == 0) {
      throw new FailedDependencyException("no response body");
    }
    T t;
    try {
      t = objectMapper.readValue(json, clazz);
    } catch (Exception ex) {
      throw new InternalServerException(ex.getMessage());
    }
    return t;
  }
}
