package com.demo.top.service.artist;

import com.demo.top.feign.ItunesService;
import com.demo.top.model.album.Album;
import com.demo.top.model.artist.Artist;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ItunesArtistService implements ArtistService {

    private final ItunesService itunesService;

    private static final Logger log = LoggerFactory.getLogger(ItunesArtistService.class);

    public ItunesArtistService(ItunesService itunesService) {
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
