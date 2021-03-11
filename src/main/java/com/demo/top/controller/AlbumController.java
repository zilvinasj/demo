package com.demo.top.controller;

import com.demo.top.model.album.Album;
import com.demo.top.model.response.ApplicationAlbumResponse;
import com.demo.top.service.album.AlbumService;
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
@RequestMapping(value = "/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @GetMapping(value = "/{albumName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationAlbumResponse> searchForAlbums(@PathVariable String albumName) {
        log.info("Searching for album: {}", albumName);
        List<Album> albumsByName = albumService.getAlbumsByName(albumName);
        log.debug("Returning albums for name: {}, artist: {}", albumName, albumsByName.stream()
                .map(Album::getCollectionName)
                .collect(Collectors.joining(",")));

        ApplicationAlbumResponse response = new ApplicationAlbumResponse();
        response.setAlbums(albumsByName);

        return ResponseEntity.ok(response);
    }

}
