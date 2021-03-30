package com.demo.top.service.artist;

import com.demo.top.model.artist.Artist;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MockArtistService implements ArtistService {

  private static final Logger log = LoggerFactory.getLogger(MockArtistService.class);

  static final Map<String, List<Artist>> artistMap = Map.of(
      "Kanye", List.of(
          new Artist.ArtistBuilder()
              .name("Kanye West")
              .id("1L")
              .popularity(10L).build(),
          new Artist.ArtistBuilder()
              .name("Kanye W")
              .id("2L")
              .popularity(20L).build(),
          new Artist.ArtistBuilder()
              .name("Kanye Wost")
              .id("3L")
              .popularity(30L).build()
      ));

//  static final Map<Long, List<Album>> albumMap = Map.of(
//      1L, List.of(
//          new Album.AlbumBuilder().collectionName("MBDTF").build(),
//          new Album.AlbumBuilder().collectionName("808s and Heartbreak").build(),
//          new Album.AlbumBuilder().collectionName("Graduation").build()
//      ));

  public List<Artist> getArtistsByName(String name) {
    log.info("Searching artists with name: {}", name);
    return artistMap.getOrDefault(name, Collections.emptyList());
  }

}
