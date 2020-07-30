package com.demo.top.service.artist;

import com.demo.top.model.album.Album;
import com.demo.top.model.artist.Artist;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MockArtistService implements ArtistService {

  private static final Logger log = LoggerFactory.getLogger(MockArtistService.class);

  static final Map<String, List<Artist>> artistMap = Map.of(
      "Kanye", List.of(
          new Artist.ArtistBuilder()
              .artistName("Kanye West")
              .amgArtistId(1L)
              .artistId(10L).build(),
          new Artist.ArtistBuilder()
              .artistName("Kanye W")
              .amgArtistId(2L)
              .artistId(20L).build(),
          new Artist.ArtistBuilder()
              .artistName("Kanye Wost")
              .amgArtistId(3L)
              .artistId(30L).build()
      ));

  static final Map<Long, List<Album>> albumMap = Map.of(
      1L, List.of(
          new Album.AlbumBuilder().collectionName("MBDTF").build(),
          new Album.AlbumBuilder().collectionName("808s and Heartbreak").build(),
          new Album.AlbumBuilder().collectionName("Graduation").build()
      ));

  public List<Artist> getArtistsByName(String name) {
    log.info("Searching artists with name: {}", name);
    return artistMap.getOrDefault(name, Collections.emptyList());
  }

  public List<Album> getTopAlbumsForArtist(Long amgArtistId, Integer limit) {
    log.info("Getting albums for artist with Id: {}", amgArtistId);
    return albumMap.getOrDefault(amgArtistId, Collections.emptyList()).stream().limit(limit).collect(Collectors.toList());
  }

}
