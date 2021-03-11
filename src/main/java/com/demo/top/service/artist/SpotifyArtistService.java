package com.demo.top.service.artist;

import com.demo.top.spotify.SpotifyService;
import com.demo.top.model.artist.Artist;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SpotifyArtistService implements ArtistService {

    private final SpotifyService spotifyService;

    private static final Logger log = LoggerFactory.getLogger(SpotifyArtistService.class);

    public SpotifyArtistService(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Cacheable(cacheNames = "artistCache", key = "#name", cacheManager = "cacheManager")
    public List<Artist> getArtistsByName(String name) {
        log.info("No cache hit for artist with name: {}, calling itunes service", name);
        return spotifyService.findArtists(name).getArtists().getItems();
    }
}
