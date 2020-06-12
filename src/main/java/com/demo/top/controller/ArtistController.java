package com.demo.top.controller;

import com.demo.top.model.album.Album;
import com.demo.top.model.artist.Artist;
import com.demo.top.model.response.ApplicationAlbumResponse;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.service.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/{artistName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationArtistResponse> searchForArtist(@PathVariable String artistName) {
        log.info("Searching for artist: {}", artistName);
        List<Artist> artistsByName = artistService.getArtistsByName(artistName);
        log.debug("Returning artists for name: {}, artist: {}", artistName, artistsByName.stream()
                .map(Artist::getArtistName)
                .collect(Collectors.joining(",")));

        ApplicationArtistResponse response = ApplicationArtistResponse.builder()
                .artists(artistsByName)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{amgArtistId}/albums", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationAlbumResponse> getTopAlbums(@PathVariable Long amgArtistId, @RequestParam(required = false, defaultValue = "5") Integer limit) {
        log.info("Looking up albums artistId: {}", amgArtistId);

        // Itunes returns a results list containing both the artist and the albums in the same array, we don't need the artist so we filter it out (assuming consumer stores artist name -> id mapping)
        List<Album> albums = artistService.getTopAlbumsForArtist(amgArtistId, limit)
                .stream()
                .dropWhile(it -> it.getCollectionId() == null)
                .collect(Collectors.toList());

        log.debug("Returning albums for artistId: {}, albums: {}", amgArtistId, albums.stream()
                .map(Album::getCollectionName)
                .collect(Collectors.joining(",")));

        ApplicationAlbumResponse response = ApplicationAlbumResponse.builder()
                .albums(albums)
                .build();

        return ResponseEntity.ok(response);
    }


}
