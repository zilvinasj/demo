package com.demo.top.itunes;

import com.demo.top.exception.FailedDependencyException;
import com.demo.top.exception.InternalServerException;
import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.ArtistSearchResponse;
import com.demo.top.model.song.SongSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ItunesGateway {

  private final RestTemplate restTemplate;

  private final String itunesUrl;

  private final ObjectMapper objectMapper;

  @Autowired
  public ItunesGateway(RestTemplate restTemplate,
      ObjectMapper objectMapper, @Value("${top-application.itunes.url}") String itunesUrl) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.itunesUrl = itunesUrl;
  }

  public ArtistSearchResponse getArtists(String artistName) {
    ResponseEntity<String> responseEntity = restTemplate
        .getForEntity(itunesUrl + "/search?entity=musicArtist&term=" + artistName, String.class);
    return jsonToObject(responseEntity.getBody(), ArtistSearchResponse.class);
  }

  public AlbumSearchResponse getAlbums(Long amgArtistId, Integer limit) {
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        itunesUrl + "/lookup?entity=album&amgArtistId=" + amgArtistId + "&limit=" + limit,
        String.class);
    return jsonToObject(responseEntity.getBody(), AlbumSearchResponse.class);
  }

  public SongSearchResponse getSongs(String songName) {
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        itunesUrl + "/search?entity=musicTrack&term=" + songName,
        String.class);
    return jsonToObject(responseEntity.getBody(), SongSearchResponse.class);
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
