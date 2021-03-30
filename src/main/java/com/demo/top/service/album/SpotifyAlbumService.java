package com.demo.top.service.album;

import com.demo.top.model.album.Album;
import com.demo.top.spotify.SpotifyService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SpotifyAlbumService implements AlbumService {

    private final SpotifyService spotifyService;

    private static final Logger log = LoggerFactory.getLogger(SpotifyAlbumService.class);

    public SpotifyAlbumService(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Cacheable(cacheNames = "albumCache", key = "#name", cacheManager = "cacheManager")
    public List<Album> getAlbumsByName(String name) {
        log.info("No cache hit for artist with name: {}, calling Spotify service", name);
        return spotifyService.findAlbums(name).getAlbums().getItems();
    }
}
