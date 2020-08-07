package com.demo.top.controller;

import com.demo.top.model.response.ApplicationSongResponse;
import com.demo.top.model.song.Song;
import com.demo.top.service.SongsService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/songs")
public class SongsController {

  private final SongsService songService;

  private static final Logger log = LoggerFactory.getLogger(SongsController.class);

  @Autowired
  public SongsController(SongsService songService) {
    this.songService = songService;
  }

  @GetMapping(value = "/{songName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApplicationSongResponse> searchForSongs(@PathVariable String songName) {
    log.info("Searching for songs: {}", songName);
    List<Song> songsByName = songService.getSongsByName(songName);
    log.debug("Returning songs for name: {}", songName);

    ApplicationSongResponse response = new ApplicationSongResponse();
    response.setSongs(songsByName);

    return ResponseEntity.ok(response);
  }


}
