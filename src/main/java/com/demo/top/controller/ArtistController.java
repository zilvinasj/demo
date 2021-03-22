package com.demo.top.controller;

import com.demo.top.model.artist.Artist;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.service.artist.ArtistService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    private static final Logger log = LoggerFactory.getLogger(ArtistController.class);

    @GetMapping(value = "/{artistName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationArtistResponse> searchForArtist(@PathVariable String artistName) {
        log.info("Searching for artist: {}", artistName);
        List<Artist> artistsByName = artistService.getArtistsByName(artistName);
        String artistNames = artistsByName.stream()
            .map(Artist::getName)
            .collect(Collectors.joining(","));
        log.debug("Returning artists for name: {}, artists: {}", artistName, artistNames);

        ApplicationArtistResponse response = new ApplicationArtistResponse();
        response.setArtists(artistsByName);

        return ResponseEntity.ok(response);
    }

}
