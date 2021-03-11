package com.demo.top.service.artist;

import com.demo.top.model.artist.Artist;
import java.util.List;

/**
 Interface for the artist service
 */
public interface ArtistService {
  List<Artist> getArtistsByName(String name);
}
