package com.demo.top.controller;

import com.demo.top.model.dto.PlaylistDTO;
import com.demo.top.model.dto.SongDTO;
import com.demo.top.service.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    private static final Logger log = LoggerFactory.getLogger(PlaylistController.class);

    @GetMapping(value = "/{playlistName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistDTO> searchForPlaylistByName(@PathVariable String playlistName) {
        log.info("Searching for playlist: {}", playlistName);

        return ResponseEntity.ok(new PlaylistDTO());
    }

    @GetMapping(value = "/{playlistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable Long playlistId) {
        log.info("Looking up albums artistId: {}", playlistId);

        // Itunes returns a results list containing both the artist and the albums in the same array, we don't need the artist so we filter it out (assuming consumer stores artist name -> id mapping)

        PlaylistDTO response = playlistService.get(playlistId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/{playlistId}/{newName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistDTO> changePlaylistName(@PathVariable Long playlistId, @PathVariable String newName) {
        log.info("Looking up albums artistId: {}", playlistId);

        // Itunes returns a results list containing both the artist and the albums in the same array, we don't need the artist so we filter it out (assuming consumer stores artist name -> id mapping)

        PlaylistDTO response = playlistService.updateName(playlistId, newName);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {

        PlaylistDTO createdPlaylist = playlistService.add(playlistDTO);

        return ResponseEntity.created(URI.create("/v1/playlist/" + createdPlaylist.getId())).body(createdPlaylist);
    }

    @PutMapping(value = "/{playlistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistDTO> addSongs(@PathVariable Long playlistId, @RequestBody Set<SongDTO> songs) {

        PlaylistDTO response = playlistService.updateSongs(playlistId, songs);

        return ResponseEntity.ok(response);
    }

}
