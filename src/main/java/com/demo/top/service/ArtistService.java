package com.demo.top.service;

import com.demo.top.feign.ItunesService;
import com.demo.top.model.album.Album;
import com.demo.top.model.artist.Artist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArtistService {

    private final ItunesService itunesService;

    public ArtistService(ItunesService itunesService) {
        this.itunesService = itunesService;
    }

    @Cacheable(cacheNames = "artistCache", key = "#name", cacheManager = "cacheManager")
    public List<Artist> getArtistsByName(String name) {
        log.info("No cache hit for artist with name: {}, calling itunes service", name);
        return itunesService.findArtist(name).getResults();
    }

    @Cacheable(cacheNames = "albumCache", key = "#amgArtistId", cacheManager = "cacheManager")
    public List<Album> getTopAlbumsForArtist(Long amgArtistId, Integer limit) {
        log.info("No cache hit for albums from artist with Id: {}, calling itunes service", amgArtistId);
        return itunesService.findTopAlbumsForArtist(amgArtistId, limit).getResults();
    }


}
